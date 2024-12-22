package com.dormitory.service;

import com.dormitory.model.User;

public interface UserService {
    User login(String username, String password);
}
