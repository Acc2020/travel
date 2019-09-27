package com.example.travel.dao;

import com.example.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    public abstract List<Category> findAll();
}
