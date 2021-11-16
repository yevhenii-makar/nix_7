package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.unit.Unit;
import com.yevheniimakar.beltcutting.repository.UnitRepository;
import com.yevheniimakar.beltcutting.service.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {

    public final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    @Override
    public Unit getById(Long id) {
        return unitRepository.findById(id).orElseThrow(()-> BeltCuttingExceptions.unitNotFound(id));
    }
}
