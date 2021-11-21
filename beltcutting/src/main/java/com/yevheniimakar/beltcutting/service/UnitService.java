package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.unit.Unit;
import com.yevheniimakar.beltcutting.model.unit.request.UnitUpdateRequest;
import com.yevheniimakar.beltcutting.model.unit.response.UnitResponse;

import java.util.List;

public interface UnitService {

    Unit getById(Long manufactureId);

    UnitResponse getResponseById(Long id);

    List<UnitResponse> getAllResponse();

    List<Unit> getAll();

    UnitResponse create(String name);

    UnitResponse update(Long id, UnitUpdateRequest request);
}
