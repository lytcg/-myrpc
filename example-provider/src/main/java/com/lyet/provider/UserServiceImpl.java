package com.lyet.provider;

import com.lyet.common.model.User;
import com.lyet.common.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("userName: "+user.getName());
        return user;
    }
}
