package com.yevheniimakar.beltcutting.controller.unit;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.model.unit.request.UnitUpdateRequest;
import com.yevheniimakar.beltcutting.model.unit.response.UnitResponse;
import com.yevheniimakar.beltcutting.service.UnitService;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Routes.UNITS)
public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnitResponse createTask(@RequestBody @Valid String request) {
        return unitService.create(request);
    }

    @PatchMapping("/{id}")
    public UnitResponse update(@PathVariable Long id, @RequestBody @Valid UnitUpdateRequest request) {
        return unitService.update(id, request);
    }

    @GetMapping
    @PageableAsQueryParam
    public List<UnitResponse> getCurrentTask() {
        return unitService.getAllResponse();
    }
}
