package com.example.travel.web.servlet;

import com.example.travel.dao.impl.UserDaoImpl;
import com.example.travel.service.UserService;
import com.example.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取 code
        String code = request.getParameter("code");
        if(code != null){
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            //判断激活状态
            String msg = null;
            if(flag){
                msg = "激活成功，请点击<a href = 'login.html'>登录</a>";
            }else {
                msg = "激活失败，请重新注册或联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
