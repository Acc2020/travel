package com.example.travel.dao;

import com.example.travel.domain.User;

public interface UserDao {
    /**
     * 通过 用户名查询用户用户信息
     * @param username
     * @return
     */
    public abstract User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     */
    public abstract  void save(User user);

    /**
     * 通过唯一码查询用户
     * @param code
     * @return
     */
    public abstract User findByCode(String code);

    /**
     * 更新用户状态
     * @param user
     */
    public abstract void updateStatus(User user);

    /**
     * 通过用户名和密码查询用户，实现登录功能
     * @param username
     * @param password
     * @return
     */
    public abstract User findByUsernameAndPassword(String username, String password);
}
