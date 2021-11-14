package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.repository.CardRepository;
import com.yevheniimakar.beltcutting.service.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;


    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card getCardById(Long id){
        return cardRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.cardNotFound(id));
    }
}
