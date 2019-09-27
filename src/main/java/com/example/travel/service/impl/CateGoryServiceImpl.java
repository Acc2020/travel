package com.example.travel.service.impl;

import com.example.travel.dao.CategoryDao;
import com.example.travel.dao.impl.CategoryDaoImpl;
import com.example.travel.domain.Category;
import com.example.travel.service.CateGoryService;
import com.example.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CateGoryServiceImpl implements CateGoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        //1、建立redis连接
        Jedis jedis = JedisUtil.getJedis();
//        Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        //2、判断是否为空
        List<Category> cs = null;
        if(categorys == null || categorys.size() == 0){
            System.out.println("从数据库中查询。。");
            cs = categoryDao.findAll();
            //把集合存入到 redis 中
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {
            System.out.println("从redis中查询。。");
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
