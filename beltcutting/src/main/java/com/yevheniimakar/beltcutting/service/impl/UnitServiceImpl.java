package com.yevheniimakar.beltcutting.service.impl;


import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.unit.Unit;
import com.yevheniimakar.beltcutting.model.unit.request.UnitUpdateRequest;
import com.yevheniimakar.beltcutting.model.unit.response.UnitResponse;
import com.yevheniimakar.beltcutting.repository.UnitRepository;
import com.yevheniimakar.beltcutting.service.UnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    @Transactional
    public Unit getById(Long id) {
        return unitRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.unitNotFound(id));
    }
    @Override
    public UnitResponse getResponseById(Long id) {
        return new UnitResponse(getById(id));
    }

    @Override
    public List<UnitResponse> getAllResponse() {
        return getAll().stream().map(UnitResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    @Override
    public UnitResponse create(String name) {
        Unit unit = new Unit();
        unit.setName(name);
        return new UnitResponse(unitRepository.save(unit));
    }

    @Override
    @Transactional
    public UnitResponse update(Long id, UnitUpdateRequest request) {
        Unit unit = getById(id);
        unit.setName(request.getName());
        return new UnitResponse(unit);
    }
}
