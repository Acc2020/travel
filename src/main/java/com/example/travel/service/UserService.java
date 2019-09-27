package com.example.travel.service;

import com.example.travel.domain.User;

public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    public abstract boolean regist(User user);

    /**
     * 用户激活
     * @param code
     * @return
     */
    public abstract boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public abstract User login(User user);
}
