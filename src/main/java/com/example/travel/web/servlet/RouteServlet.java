package com.example.travel.web.servlet;

import com.example.travel.domain.PageBean;
import com.example.travel.domain.Route;
import com.example.travel.domain.User;
import com.example.travel.service.FavoriteService;
import com.example.travel.service.RouteService;
import com.example.travel.service.impl.FavoriteServiceImpl;
import com.example.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //接受rname 线路名称
        String rname = request.getParameter("rname");
        // rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        int cid = 0;//类别id
        //2.处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        //3. 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);
        //4. 将pageBean对象序列化为json，返回
        writeValue(pb, response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收id
        String rid = request.getParameter("rid");
        //2.调用service查询route对象
        Route route = routeService.findOne(rid);
        //3.转为json写回客户端
        writeValue(route, response);
    }

    /**
     * 判断当前登录用户是否收藏路线
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取 路线id 和登录用户
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");

        //判断用户是否是空
        int uid;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();
        }
        //调用 service层方法
        boolean flag = favoriteService.isFavorite(rid, uid);

        //写回客户端
        writeValue(flag, response);
    }
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");

        //判断
        int uid;
        if(user == null){
            return;
        } else {
            uid = user.getUid();
        }
        favoriteService.add(rid, uid);
    }
}
