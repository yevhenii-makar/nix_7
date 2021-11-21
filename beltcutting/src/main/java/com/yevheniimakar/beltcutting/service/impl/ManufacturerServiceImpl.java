package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.manufacturer.Manufacturer;
import com.yevheniimakar.beltcutting.model.manufacturer.request.ManufacturerUpdateRequest;
import com.yevheniimakar.beltcutting.model.manufacturer.response.ManufacturerResponse;
import com.yevheniimakar.beltcutting.repository.ManufacturerRepository;
import com.yevheniimakar.beltcutting.service.ManufacturerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    @Transactional
    public Manufacturer getById(Long id) {
        return manufacturerRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.manufacturerNotFound(id));
    }
    @Override
    public ManufacturerResponse getResponseById(Long id) {
        return new ManufacturerResponse(getById(id));
    }

    @Override
    public List<ManufacturerResponse> getAll() {
        return manufacturerRepository.findAll().stream().map(ManufacturerResponse::new).collect(Collectors.toList());
    }

    @Override
    public ManufacturerResponse create(String name) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        return new ManufacturerResponse(manufacturerRepository.save(manufacturer));
    }

    @Override
    @Transactional
    public ManufacturerResponse update(Long id, ManufacturerUpdateRequest request) {
        Manufacturer manufacturer = getById(id);
        manufacturer.setName(request.getName());
        return new ManufacturerResponse(manufacturer);
    }
}
