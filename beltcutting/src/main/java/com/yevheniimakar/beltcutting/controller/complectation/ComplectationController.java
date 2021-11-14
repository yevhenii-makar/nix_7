package com.yevheniimakar.beltcutting.controller.complectation;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.model.complectation.response.ComplectationTemplateResponse;
import com.yevheniimakar.beltcutting.service.ComplectationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(Routes.COMPLECTATION)
public class ComplectationController {

    ComplectationService complectationService;

    public ComplectationController(ComplectationService complectationService) {
        this.complectationService = complectationService;
    }

    @GetMapping("/template")
    public ComplectationTemplateResponse getComplectationTeplate (Long cardId, @Min(value = 1, message = "The value must be positive") int count, Authentication authentication){
        return complectationService.getComplectationTemplateResponseByCardIdAndCount(cardId, count, authentication);
    }



}
