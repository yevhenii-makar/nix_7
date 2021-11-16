package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.card.request.CardCreateRequest;
import com.yevheniimakar.beltcutting.model.card.response.CardResponseSingle;
import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface CardService {

    Card getCardById(Long id);
    
    CardResponseSingle getCardResponseById(Long id);

    Page<CardResponseViewInList> getAllCards(Pageable pageable);

    CardResponseSingle create(CardCreateRequest request, Authentication authentication);
}
