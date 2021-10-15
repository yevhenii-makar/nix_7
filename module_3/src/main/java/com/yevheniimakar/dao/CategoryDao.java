package com.yevheniimakar.dao;

import com.yevheniimakar.entity.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getAll();

    Category getCategoryById(Long id);
}
