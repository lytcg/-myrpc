package com.lyet.myrpc.server;

import com.lyet.myrpc.RpcApplication;
import com.lyet.myrpc.model.RpcRequest;
import com.lyet.myrpc.model.RpcResponse;
import com.lyet.myrpc.registry.LocalRegistry;
import com.lyet.myrpc.serializer.JdkSerializer;
import com.lyet.myrpc.serializer.Serializer;
import com.lyet.myrpc.serializer.SerializerFactory;
import com.sun.net.httpserver.HttpHandler;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * HTTP 请求响应
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest request) {
        //指定序列化器
        final Serializer serializer= SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        //记录日志
        System.out.println("Received request: "+request.method()+"　"+request.uri());
        //异步处理http请求

        request.bodyHandler(body->{
            byte[] bytes=body.getBytes();
            RpcRequest rpcRequest=null;

            try {
                rpcRequest=serializer.deserialize(bytes,RpcRequest.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //构建响应结果
            RpcResponse rpcResponse=new RpcResponse();
            if(rpcRequest==null){
                rpcResponse.setMessage("rpcRequest is null");
                doResPonse(request,rpcResponse,serializer);
                return;
            }

            try {
                Class<?> clazz= LocalRegistry.get(rpcRequest.getServiceName());
                Method method=clazz.getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
                Object res=method.invoke(clazz.newInstance(),rpcRequest.getArgs());
                //封装返回结果
                rpcResponse.setData(res);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setData(e.getMessage());
                rpcResponse.setException(e);
            }

            doResPonse(request,rpcResponse,serializer);
        });

    }

    /**
     * 响应
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    private void doResPonse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse=request.response().putHeader("content-type","application/json");

        try {
            //序列化
            byte[] bytes=serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}