package com.project.bookstore.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}