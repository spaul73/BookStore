package com.project.bookstore.service;

import com.project.bookstore.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}