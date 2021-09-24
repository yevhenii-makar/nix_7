package com.yevheniimakar.dao;

import com.yevheniimakar.entity.Problem;

import java.util.List;


public interface ProblemDAO {

    List<Problem> getAllWithoutSolutions();

}
