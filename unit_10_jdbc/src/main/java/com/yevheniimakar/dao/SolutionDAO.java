package com.yevheniimakar.dao;

import com.yevheniimakar.entity.Solution;

import java.util.List;


public interface SolutionDAO {

    void save(Solution solution);
    void saveAll(List<Solution> solutions);

}
