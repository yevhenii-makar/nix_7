package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.complectation.Complectation;
import com.yevheniimakar.beltcutting.model.complectation.request.ComplectationUpdateRequest;
import com.yevheniimakar.beltcutting.model.complectation.response.ComplectationResponse;
import com.yevheniimakar.beltcutting.model.complectation.response.ComplectationTemplateResponse;
import com.yevheniimakar.beltcutting.model.task.Task;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ComplectationService {

    List<ComplectationResponse> getComplectationResponseByTaskId(Long taskId, Authentication authentication);

    ComplectationTemplateResponse getComplectationTemplateResponseByCardIdAndCount(Long cardId, int count, Authentication authentication);

    List<ComplectationResponse> saveComplectationResponseList(Long taskId, List<ComplectationUpdateRequest> complectationUpdateRequestList, Authentication authentication);

    List<Complectation> saveComplectationList(Long taskId, List<ComplectationUpdateRequest> complectationUpdateRequestList);


}
