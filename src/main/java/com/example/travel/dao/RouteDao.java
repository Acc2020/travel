package com.example.travel.dao;


import com.example.travel.domain.Route;
import java.util.List;

public interface RouteDao {

    /**
     * 根据cid查询总记录数
     */
    public abstract int findTotalCount(int cid, String rname);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     */
    public abstract List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public abstract Route findOne(int rid);
}
