package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.equipment.Equipment;
import com.yevheniimakar.beltcutting.model.equipment.request.EquipmentCreateRequest;
import com.yevheniimakar.beltcutting.model.equipment.request.EquipmentUpdateRequest;
import com.yevheniimakar.beltcutting.model.equipment.response.EquipmentResponse;
import com.yevheniimakar.beltcutting.model.equipment.response.EquipmentTemplateResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface EquipmentService {

    List<EquipmentResponse> getEquipmentResponseByTaskId(Long taskId, Authentication authentication);

    EquipmentTemplateResponse getEquipmentTemplateResponseByCardIdAndCount(Long cardId, int count, Authentication authentication);

    List<EquipmentResponse> saveEquipmentResponseList(Long taskId, List<EquipmentUpdateRequest> equipmentUpdateRequestList, Authentication authentication);

    List<Equipment> saveEquipmentList(Long taskId, List<EquipmentUpdateRequest> equipmentUpdateRequestList);

    EquipmentResponse create(Long taskId, EquipmentCreateRequest request);
}
