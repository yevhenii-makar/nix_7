package com.yevheniimakar.service.impl;

import com.yevheniimakar.dao.CategoryDao;
import com.yevheniimakar.dao.impl.CategoryDaoImpl;
import com.yevheniimakar.dto.CategoryDto;
import com.yevheniimakar.dto.ConsumptionCategoryDto;
import com.yevheniimakar.dto.IncomeCategoryDto;
import com.yevheniimakar.entity.Category;
import com.yevheniimakar.entity.ConsumptionCategory;
import com.yevheniimakar.entity.IncomeCategory;
import com.yevheniimakar.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao;

    public CategoryServiceImpl() {
        this.categoryDao = new CategoryDaoImpl();
    }


    @Override
    public List<CategoryDto> getCategoryDtoListFromCategoryList(List<Category> categoryList) {

        List<CategoryDto> categoryDtoList = new ArrayList<>(categoryList.size());


        for (Category category: categoryList) {
            categoryDtoList.add(getCategoryDtoFromCategory(category));
        }
        return categoryDtoList;

    }

    @Override
    public List<CategoryDto> getAll() {
        return getCategoryDtoListFromCategoryList(categoryDao.getAll());
    }


    private CategoryDto getCategoryDtoFromCategory(Category category){
        CategoryDto categoryDto = null;
        if (category instanceof IncomeCategory){
            categoryDto= new IncomeCategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(((IncomeCategory)category).getName());

        } else if (category instanceof ConsumptionCategory){
            categoryDto= new ConsumptionCategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(((ConsumptionCategory)category).getName());
        }
        return categoryDto;
    }
}
