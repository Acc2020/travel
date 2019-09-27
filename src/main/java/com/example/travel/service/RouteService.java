package com.example.travel.service;

import com.example.travel.domain.PageBean;
import com.example.travel.domain.Route;

public interface RouteService {
    /**
     * 分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public abstract PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);
    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public abstract Route findOne(String rid);
}
