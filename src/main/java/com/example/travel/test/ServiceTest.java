package com.example.travel.test;

import com.example.travel.dao.UserDao;
import com.example.travel.domain.User;
import com.example.travel.service.UserService;
import com.example.travel.service.impl.UserServiceImpl;
import org.junit.Test;

public class ServiceTest {


    /**
     * 测试用户注册
     */
    @Test
    public void testRegist(){
        User user = new User();
        user.setUid(1);
        user.setUsername("zhangsanli");
        user.setPassword("lishi");
        user.setEmail("example@admin.com");

        UserServiceImpl service  = new UserServiceImpl();
        service.regist(user);
    }

    /**
     * 测试登录
     */
    @Test
    public void testLogin(){
        User user = new User();
        user.setUid(10);
        user.setUsername("zhangsanli");
        user.setPassword("lishi");
        user.setEmail("18226568070@139.com");
        user.setStatus("Y");


        UserService service = new UserServiceImpl();
        service.regist(user);
        service.login(user);
    }

}
