package com.yevheniimakar.dao.impl;

import com.yevheniimakar.config.DataSource;
import com.yevheniimakar.dao.CategoryDao;
import com.yevheniimakar.entity.Account;
import com.yevheniimakar.entity.Category;

import com.yevheniimakar.exeption.ObjectNotFoundException;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CategoryDaoImpl implements CategoryDao {

    SessionFactory sessionFactory;

    public CategoryDaoImpl() {
        this.sessionFactory = DataSource.getSessionFactory();
    }

    @Override
    public List<Category> getAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        TypedQuery<Category> query = entityManager.createQuery("from Category", Category.class);
        List<Category> categoryList = query.getResultList();

        if (categoryList != null){
            entityManager.close();
            return categoryList;
        } else {
            throw new ObjectNotFoundException("Category not Found");
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        Category category = entityManager.find(Category.class, id);

        if (category != null){
            entityManager.close();
            return category;
        } else {
            throw new ObjectNotFoundException("Category with id: "  + id +" notFound");
        }

    }
}
