package com.example.travel.service;

public interface FavoriteService {
    /**
     * 通过 rid 和 uid 判断是否存在收藏
     * @param rid
     * @param uid
     * @return
     */
    public abstract boolean isFavorite(String rid, int uid);

    /**
     * 增加收藏数据
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
