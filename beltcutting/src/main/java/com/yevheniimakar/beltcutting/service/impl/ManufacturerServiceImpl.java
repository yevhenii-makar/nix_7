package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.Manufacturer;
import com.yevheniimakar.beltcutting.repository.ManufacturerRepository;
import com.yevheniimakar.beltcutting.service.ManufacturerService;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer getById(Long id) {
        return manufacturerRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.manufacturerNotFound(id));
    }
}
