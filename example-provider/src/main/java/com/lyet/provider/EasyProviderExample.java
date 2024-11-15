package com.lyet.provider;

import com.lyet.common.service.UserService;
import com.lyet.myrpc.registry.LocalRegistry;
import com.lyet.myrpc.server.HttpServer;
import com.lyet.myrpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        // 提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}