package com.example.travel.dao.impl;

import com.example.travel.dao.FavoriteDao;
import com.example.travel.domain.Favorite;
import com.example.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 通过 uid rid 查找 favorite 的数据
     *
     * @param uid
     * @param rid
     * @return
     */
    @Override
    public Favorite findByUidAndRid(int uid, int rid) {
        Favorite favorite = null;
        try{
            String sql = "select * from tab_favorite where rid = ? and uid = ? ";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), uid, rid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return favorite;
    }

    /**
     * 通过 rid 搜索收藏次数
     *
     * @param rid
     * @return
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "select Count(*) from tab_favorite where rid = ? ";
        return template.queryForObject(sql, Integer.class, rid);
    }

    /**
     * 添加收藏
     *
     * @param parseInt
     * @param uid
     */
    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?, ?, ?)";
        template.update(sql,rid,new Date(), uid);
    }
}
