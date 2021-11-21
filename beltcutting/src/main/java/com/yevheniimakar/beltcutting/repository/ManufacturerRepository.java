package com.yevheniimakar.beltcutting.repository;

import com.yevheniimakar.beltcutting.model.manufacturer.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
