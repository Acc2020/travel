package com.example.travel.web.servlet;

import com.example.travel.domain.ResultInfo;
import com.example.travel.domain.User;
import com.example.travel.service.UserService;
import com.example.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取用户信息
        Map<String, String[]> map = request.getParameterMap();

        //2、用户信息封装到 user 中
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3、判断用户是否存在
        UserService service = new UserServiceImpl();
        User u = service.login(user);

        ResultInfo info = new ResultInfo();


        //4、判断 user 是否为空进行反馈
        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }

        if(u != null && !"Y".equals(u.getStatus())){
           info.setFlag(false);
           info.setErrorMsg("尚未激活，请激活");
        }
        if(u != null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user",u);
            //登录成功
            info.setFlag(true);
        }
        //数据响应
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
