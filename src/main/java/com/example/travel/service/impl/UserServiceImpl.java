package com.example.travel.service.impl;

import com.example.travel.dao.UserDao;
import com.example.travel.dao.impl.UserDaoImpl;
import com.example.travel.domain.User;
import com.example.travel.service.UserService;
import com.example.travel.util.MailUtils;
import com.example.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDaoImpl dao = new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        User u = dao.findByUsername(user.getUsername());
        //判断是否注册成功
        if(u!=null){
            return false;
        }
        //注册成功保存信息
        //设置状态码和状态
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        dao.save(user);
        //发送邮件
        String content = "<a href='http://localhost:8080//user/active?code="+user.getCode()+"'>点击进行激活 Now旅游网 </a>";
        MailUtils.sendMail(user.getEmail(), content,"Now旅游网激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        //使用激活码查询用户对象
        User user = dao.findByCode(code);
        System.out.println(user);
        if(user != null){
            dao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return   dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
