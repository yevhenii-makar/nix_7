package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.complectation.Complectation;
import com.yevheniimakar.beltcutting.model.complectation.request.ComplectationUpdateRequest;
import com.yevheniimakar.beltcutting.model.complectation.response.ComplectationResponse;
import com.yevheniimakar.beltcutting.model.complectation.response.ComplectationTemplateResponse;
import com.yevheniimakar.beltcutting.model.piece.Piece;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.repository.CardRepository;
import com.yevheniimakar.beltcutting.repository.ComplectationRepository;
import com.yevheniimakar.beltcutting.repository.PieceRepository;
import com.yevheniimakar.beltcutting.repository.TaskRepository;
import com.yevheniimakar.beltcutting.service.ComplectationService;
import com.yevheniimakar.beltcutting.service.UserAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplectationServiceImpl implements ComplectationService {

    private static final Logger log = LoggerFactory.getLogger(ComplectationServiceImpl.class);

    private final ComplectationRepository complectationRepository;
    private final TaskRepository taskRepository;
    private final PieceRepository pieceRepository;
    private final CardRepository cardRepository;
    private final UserAuthenticationService userAuthenticationService;

    public ComplectationServiceImpl(ComplectationRepository complectationRepository, TaskRepository taskRepository, PieceRepository pieceRepository, CardRepository cardRepository, UserAuthenticationService userAuthenticationService) {
        this.complectationRepository = complectationRepository;
        this.taskRepository = taskRepository;
        this.pieceRepository = pieceRepository;
        this.cardRepository = cardRepository;
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public List<ComplectationResponse> getComplectationResponseByTaskId(Long taskId, Authentication authentication) {
        return null;
    }

    @Override
    public ComplectationTemplateResponse getComplectationTemplateResponseByCardIdAndCount(Long cardId, int count, Authentication authentication) {

        if (!(userAuthenticationService.isTechnicalSpecialist(authentication) || userAuthenticationService.isAdmin(authentication))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Doт`t have permission to do getComplectationTemplat");
        }
        if (count <= 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Doт`t have permission to do getComplectationTemplat");
        }
        Card card = cardRepository.findById(cardId).orElseThrow(() -> BeltCuttingExceptions.cardNotFound(cardId));
        int size = card.getSize() * count;

        return new ComplectationTemplateResponse(size, card.getAccessory());
    }

    @Override
    @Transactional
    public List<ComplectationResponse> saveComplectationResponseList(Long taskId, List<ComplectationUpdateRequest> complectationUpdateRequestList, Authentication authentication) {
        return saveComplectationList(taskId, complectationUpdateRequestList).stream().map(ComplectationResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Complectation> saveComplectationList(Long taskId, List<ComplectationUpdateRequest> complectationUpdateRequestList) {

        if (complectationUpdateRequestList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Complectation for task id " + taskId + " is empty");
        }

        List<Complectation> complectations = new ArrayList<>(complectationUpdateRequestList.size());
        Task task = getTask(taskId);

        List<Piece> pieces = getPieceList(complectationUpdateRequestList.stream()
                .map(o -> o.getPieceId()).distinct().collect(Collectors.toList()));
        List<Card> cards = getCardList(complectationUpdateRequestList.stream()
                .map(o -> o.getCardId()).distinct().collect(Collectors.toList()));

        for (ComplectationUpdateRequest cr : complectationUpdateRequestList) {
            Complectation complectation = new Complectation();
            complectation.setId(cr.getId());
            complectation.setSize(cr.getSize());
            complectation.setTask(task);
            complectation.setPiece(pieces.stream().filter(o -> o.getId() == cr.getPieceId()).findFirst().orElse(null));
            complectation.setCard(cards.stream().filter(o -> o.getId() == cr.getCardId()).findFirst().orElse(null));
            complectations.add(complectation);
        }
        isCorrectComlectation(complectations);

        return complectations;
    }

    private Piece getPiece(Long id) {
        return pieceRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.pieceNotFound(id));
    }

    private List<Piece> getPieceList(List<Long> pieceId) {
        return pieceRepository.findByIdIn(pieceId);
    }

    private List<Card> getCardList(List<Long> cardId) {
        return cardRepository.findByIdIn(cardId);
    }

    private Card getCard(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.cardNotFound(id));
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.taskNotFound(id));
    }

    @Transactional
    private void isCorrectComlectation(List<Complectation> complectations) {
        for (Complectation c : complectations) {
            int sizeSumm = complectations.stream().filter(o -> o.equals(c.getPiece())).map(o -> o.getSize()).reduce(0, (a, b) -> a + b);
            int count = complectations.stream().filter(o -> o.equals(c.getCard())).map(o -> o.getSize()).reduce(0, (a, b) -> a + b);
            if (sizeSumm > c.getPiece().getSize()) {
                throw BeltCuttingExceptions.pieceNotEnough(c.getPiece().getPiecesNumber(),c.getPiece().getId(), c.getCard().getId());
            }
            if (count > c.getCard().getCount()) {
                throw BeltCuttingExceptions.countCardNotEnough(c.getCard().getId());
            }
        }
    }

}
