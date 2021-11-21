package com.yevheniimakar.beltcutting.controller.manufacturer;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.model.manufacturer.request.ManufacturerUpdateRequest;
import com.yevheniimakar.beltcutting.model.manufacturer.response.ManufacturerResponse;
import com.yevheniimakar.beltcutting.service.ManufacturerService;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Routes.MANUFACTURERS)
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturerResponse createTask(@RequestBody @Valid String request) {
        return manufacturerService.create(request);
    }

    @PatchMapping("/{id}")
    public ManufacturerResponse update(@PathVariable Long id, @RequestBody @Valid ManufacturerUpdateRequest request) {
        return manufacturerService.update(id, request);
    }

    @GetMapping
    @PageableAsQueryParam
    public List<ManufacturerResponse> getCurrentTask() {
        return manufacturerService.getAll();
    }
}
