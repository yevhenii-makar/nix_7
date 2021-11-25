package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.equipment.Equipment;
import com.yevheniimakar.beltcutting.model.equipment.request.EquipmentCreateRequest;
import com.yevheniimakar.beltcutting.model.equipment.request.EquipmentUpdateRequest;
import com.yevheniimakar.beltcutting.model.equipment.response.EquipmentResponse;
import com.yevheniimakar.beltcutting.model.equipment.response.EquipmentTemplateResponse;
import com.yevheniimakar.beltcutting.model.piece.Piece;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.repository.CardRepository;
import com.yevheniimakar.beltcutting.repository.EquipmentRepository;
import com.yevheniimakar.beltcutting.repository.PieceRepository;
import com.yevheniimakar.beltcutting.repository.TaskRepository;
import com.yevheniimakar.beltcutting.service.EquipmentService;
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
public class EquipmentServiceImpl implements EquipmentService {

    private static final Logger log = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    private final EquipmentRepository equipmentRepository;
    private final TaskRepository taskRepository;
    private final PieceRepository pieceRepository;
    private final CardRepository cardRepository;
    private final UserAuthenticationService userAuthenticationService;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, TaskRepository taskRepository, PieceRepository pieceRepository, CardRepository cardRepository, UserAuthenticationService userAuthenticationService) {
        this.equipmentRepository = equipmentRepository;
        this.taskRepository = taskRepository;
        this.pieceRepository = pieceRepository;
        this.cardRepository = cardRepository;
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public List<EquipmentResponse> getEquipmentResponseByTaskId(Long taskId, Authentication authentication) {
        return null;
    }

    @Override
    public EquipmentTemplateResponse getEquipmentTemplateResponseByCardIdAndCount(Long cardId, int count, Authentication authentication) {

        if (!(userAuthenticationService.isTechnicalSpecialist(authentication) || userAuthenticationService.isAdmin(authentication))) {
            throw BeltCuttingExceptions.notHavingNecessaryPermissionsToDoEquipmentTemplate();

        }
        if (count <= 0) {
            throw BeltCuttingExceptions.countMustByPosetive();

        }
        Card card = cardRepository.findById(cardId).orElseThrow(() -> BeltCuttingExceptions.cardNotFound(cardId));
        int size = card.getSize() * count;

        return new EquipmentTemplateResponse(size, card.getAccessory());
    }

    @Override
    @Transactional
    public List<EquipmentResponse> saveEquipmentResponseList(Long taskId, List<EquipmentUpdateRequest> equipmentUpdateRequestList, Authentication authentication) {
        return saveEquipmentList(taskId, equipmentUpdateRequestList).stream().map(EquipmentResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Equipment> saveEquipmentList(Long taskId, List<EquipmentUpdateRequest> equipmentUpdateRequestList) {

        if (equipmentUpdateRequestList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Equipment for task id " + taskId + " is empty");
        }

        List<Equipment> equipments = new ArrayList<>(equipmentUpdateRequestList.size());
        Task task = getTask(taskId);

        List<Piece> pieces = getPieceList(equipmentUpdateRequestList.stream()
                .map(o -> o.getPieceId()).distinct().collect(Collectors.toList()));
        List<Card> cards = getCardList(equipmentUpdateRequestList.stream()
                .map(o -> o.getCardId()).distinct().collect(Collectors.toList()));

        for (EquipmentUpdateRequest cr : equipmentUpdateRequestList) {
            Equipment equipment = new Equipment();
            equipment.setId(cr.getId());
            equipment.setSize(cr.getSize());
            equipment.setTask(task);
            equipment.setPiece(pieces.stream().filter(o -> o.getId() == cr.getPieceId()).findFirst().orElse(null));
            equipment.setCard(cards.stream().filter(o -> o.getId() == cr.getCardId()).findFirst().orElse(null));
            equipments.add(equipment);
        }
        isCorrectComlectation(equipments);

        return equipments;
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

    @Override
    public EquipmentResponse create(Long taskId, EquipmentCreateRequest request) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> BeltCuttingExceptions.taskNotFound(taskId));
        Card card = cardRepository.findById(request.getCardId()).orElseThrow(() -> BeltCuttingExceptions.cardNotFound(request.getCardId()));
        Piece piece = pieceRepository.findById(request.getPieceId()).orElseThrow(() -> BeltCuttingExceptions.pieceNotFound(request.getPieceId()));
        Equipment equipment = new Equipment();
        equipment.setTask(task);
        equipment.setCard(card);
        equipment.setPiece(piece);
        equipment.setSize(request.getSize());

        return new EquipmentResponse(equipmentRepository.save(equipment));

    }

    @Transactional
    void isCorrectComlectation(List<Equipment> equipments) {
        for (Equipment c : equipments) {
            int sizeSumm = equipments.stream().filter(o -> o.equals(c.getPiece())).map(o -> o.getSize()).reduce(0, (a, b) -> a + b);
            int count = equipments.stream().filter(o -> o.equals(c.getCard())).map(o -> o.getSize()).reduce(0, (a, b) -> a + b);
            if (sizeSumm > c.getPiece().getSize()) {
                throw BeltCuttingExceptions.pieceNotEnough(c.getPiece().getPiecesNumber(), c.getPiece().getId(), c.getCard().getId());
            }
            if (count > c.getCard().getCount()) {
                throw BeltCuttingExceptions.countCardNotEnough(c.getCard().getId());
            }
        }
    }

}
