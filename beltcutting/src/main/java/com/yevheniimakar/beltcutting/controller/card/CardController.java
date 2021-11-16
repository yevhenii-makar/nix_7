package com.yevheniimakar.beltcutting.controller.card;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.model.card.request.CardCreateRequest;
import com.yevheniimakar.beltcutting.model.card.response.CardResponseSingle;
import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;
import com.yevheniimakar.beltcutting.service.CardService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.CARDS)
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    public CardResponseSingle getCardById(@PathVariable Long id, Authentication authentication){
        return cardService.getCardResponseById(id);
    }

    @GetMapping
    @PageableAsQueryParam
    public Page<CardResponseViewInList> getAllCard(@Parameter(hidden = true) Pageable pageable, Authentication authentication){
        return cardService.getAllCards(pageable);
    }

    @PostMapping
    public CardResponseSingle createCard(@RequestBody @Valid CardCreateRequest request, Authentication authentication){
        return cardService.create(request, authentication);
    }



}
