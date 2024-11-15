package com.lyet.consumer;


import com.lyet.common.model.User;
import com.lyet.common.service.UserService;
import com.lyet.myrpc.proxy.ServiceProxyFactory;

/**
 * 简易服务消费者示例
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        // todo 需要获取 UserService 的实现类对象
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("输出这个代表程序运行成功：：：：：：LYET");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }

        System.out.println("这里是测试的输出L "+userService.getNumber());
    }
}