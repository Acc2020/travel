package com.example.travel.service.impl;

import com.example.travel.dao.FavoriteDao;
import com.example.travel.dao.impl.FavoriteDaoImpl;
import com.example.travel.domain.Favorite;
import com.example.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl();
    /**
     * 通过 rid 和 uid 判断是否存在收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = dao.findByUidAndRid(Integer.parseInt(rid), uid);
        return favorite != null;
    }

    /**
     * 增加收藏数据
     *
     * @param rid
     * @param uid
     */
    @Override
    public void add(String rid, int uid) {
        dao.add(Integer.parseInt(rid),uid);
    }
}
