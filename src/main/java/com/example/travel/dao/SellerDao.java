package com.example.travel.dao;


import com.example.travel.domain.Seller;

public interface SellerDao {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public  abstract  Seller findById(int id);
}
