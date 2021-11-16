package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.card.request.CardCreateRequest;
import com.yevheniimakar.beltcutting.model.card.response.CardResponseSingle;
import com.yevheniimakar.beltcutting.model.card.response.CardResponseViewInList;
import com.yevheniimakar.beltcutting.repository.CardRepository;
import com.yevheniimakar.beltcutting.service.CardService;
import com.yevheniimakar.beltcutting.service.ManufacturerService;
import com.yevheniimakar.beltcutting.service.UnitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final ManufacturerService manufacturerService;

    private final UnitService unitService;

    public CardServiceImpl(CardRepository cardRepository, ManufacturerService manufacturerService, UnitService unitService) {
        this.cardRepository = cardRepository;
        this.manufacturerService = manufacturerService;
        this.unitService = unitService;
    }

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.cardNotFound(id));
    }

    @Override
    public CardResponseSingle getCardResponseById(Long id) {
        return new CardResponseSingle(getCardById(id));
    }

    @Override
    public Page<CardResponseViewInList> getAllCards(Pageable pageable) {
        return cardRepository.findAll(pageable).map(CardResponseViewInList::new);
    }

    @Override
    public CardResponseSingle create(CardCreateRequest request, Authentication authentication) {
        Card card = new Card();
        card.setName(request.getName());
        card.setCount(0);
        card.setSize(request.getSize());
        card.setManufacturer(manufacturerService.getById(request.getManufactureId()));
        card.setUnit(unitService.getById(request.getUnitId()));

        if (request.getAccessoryId() != null) {
            card.setAccessory(getCardById(request.getAccessoryId()));
        }

        return new CardResponseSingle(cardRepository.save(card));
    }
}
