package com.example.travel.dao;

import com.example.travel.domain.Favorite;

public interface FavoriteDao {
    /**
     * 通过 uid rid 查找 favorite 的数据
     * @param uid
     * @param rid
     * @return
     */
    public abstract Favorite findByUidAndRid(int uid, int rid);

    /**
     * 通过 rid 搜索收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    /**
     * 添加收藏
     * @param parseInt
     * @param uid
     */
    void add(int parseInt, int uid);
}
