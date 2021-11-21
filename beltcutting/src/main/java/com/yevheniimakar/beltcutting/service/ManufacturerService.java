package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.manufacturer.Manufacturer;
import com.yevheniimakar.beltcutting.model.manufacturer.request.ManufacturerUpdateRequest;
import com.yevheniimakar.beltcutting.model.manufacturer.response.ManufacturerResponse;

import java.util.List;

public interface ManufacturerService {

    Manufacturer getById(Long manufactureId);

    ManufacturerResponse getResponseById(Long id);

    List<ManufacturerResponse> getAll();

    ManufacturerResponse create(String name);

    ManufacturerResponse update(Long id, ManufacturerUpdateRequest request);

}
