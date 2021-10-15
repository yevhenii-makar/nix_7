package com.yevheniimakar.service;

import com.yevheniimakar.dto.CategoryDto;
import com.yevheniimakar.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategoryDtoListFromCategoryList(List<Category> categoryList);

    List<CategoryDto> getAll();
}
