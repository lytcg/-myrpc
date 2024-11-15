//package com.lyet.consumer;
//
//import com.lyet.common.model.User;
//import com.lyet.common.service.UserService;
//import com.lyet.myrpc.config.RpcConfig;
//import com.lyet.myrpc.proxy.ServiceProxyFactory;
//import com.lyet.myrpc.utils.ConfigUtils;
//
//
//public class ConsumerExample {
//
//    public static void main(String[] args) {
//        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
//        System.out.println(rpc);
//
//        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
//
//        User user = new User();
//        user.setName("LYET");
//        // 调用
//        User newUser = userService.getUser(user);
//        if (newUser != null) {
//            System.out.println(newUser.getName());
//        } else {
//            System.out.println("user == null");
//        }
//    }
//}
