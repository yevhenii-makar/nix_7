package com.yevheniimakar.beltcutting.controller.equipment;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.model.equipment.request.EquipmentCreateRequest;
import com.yevheniimakar.beltcutting.model.equipment.response.EquipmentResponse;
import com.yevheniimakar.beltcutting.model.equipment.response.EquipmentTemplateResponse;
import com.yevheniimakar.beltcutting.service.EquipmentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(Routes.COMPLECTATIONS)
public class EquipmentController {

    EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/template")
    public EquipmentTemplateResponse getEquipmentTeplate(Long cardId, @Min(value = 1, message = "The value must be positive") int count, Authentication authentication) {
        return equipmentService.getEquipmentTemplateResponseByCardIdAndCount(cardId, count, authentication);
    }

    @PostMapping("/task//{id}")
    public EquipmentResponse createEquipment(@RequestBody @Valid EquipmentCreateRequest request, @PathVariable Long id) {
        return equipmentService.create(id, request);
    }

}
