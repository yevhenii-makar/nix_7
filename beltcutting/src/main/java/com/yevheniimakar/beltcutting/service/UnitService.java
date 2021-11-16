package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.unit.Unit;

import java.util.List;

public interface UnitService {

    List<Unit> getAll();
    Unit getById(Long id);
}
