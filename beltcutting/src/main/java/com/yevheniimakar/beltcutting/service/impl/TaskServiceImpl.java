package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.equipment.Equipment;
import com.yevheniimakar.beltcutting.model.piece.Piece;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.request.TaskCreateRequest;
import com.yevheniimakar.beltcutting.model.task.request.TaskUpdateRequest;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseSingle;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseViewInList;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import com.yevheniimakar.beltcutting.repository.CardRepository;
import com.yevheniimakar.beltcutting.repository.TaskRepository;
import com.yevheniimakar.beltcutting.service.EquipmentService;
import com.yevheniimakar.beltcutting.service.TaskService;
import com.yevheniimakar.beltcutting.service.UserAuthenticationService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final CardRepository cardRepository;
    private final EquipmentService equipmentService;
    private final UserAuthenticationService userAuthenticationService;
    private final UserServiceImpl userService;

    public TaskServiceImpl(TaskRepository taskRepository, CardRepository cardRepository, EquipmentService equipmentService, UserAuthenticationService userAuthenticationService, UserServiceImpl userService) {
        this.taskRepository = taskRepository;
        this.cardRepository = cardRepository;
        this.equipmentService = equipmentService;
        this.userAuthenticationService = userAuthenticationService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Page<TaskResponseViewInList> list(Pageable pageable) {
        return taskRepository.findAll(pageable).map(TaskResponseViewInList::new);
    }

    @Override
    @Transactional
    public Page<TaskResponseViewInList> getUserTaskList(Pageable pageable, Authentication authentication) {
        List<List> statuses = userAuthenticationService.getStatuses(authentication);
        BeltCuttingUser beltCuttingUser = userService.getUser(authentication);
        return taskRepository.findByTaskStatusListAndUser(beltCuttingUser, statuses.get(0), statuses.get(1), pageable).map(o -> {
            return new TaskResponseViewInList((Task) o);
        });
    }

    @Override
    public TaskResponseSingle update(Long id, TaskUpdateRequest request, Authentication authentication) {

        BeltCuttingUser beltCuttingUser = userService.getUser(authentication);
        Task task = getTask(id);

        if ((userAuthenticationService.isManager(authentication)
                && userAuthenticationService.getManagerStatuses().contains(task.getStatus())
                && beltCuttingUser.equals(task.getBeltCuttingUser()))
                || userAuthenticationService.isAdmin(authentication)) {
            if (task.getStatus().equals(TaskStatus.CREATED)) {
                task.setName(request.getName());
                task.setMessage(request.getMessage());
            } else {
                task.setMessage(task.getMessage() + "\n" + request.getMessage());
            }
            task.setName(request.getName());
            task.setCount(request.getCount());
            task.setCard(getCard(request.getCardId()));
            task.setUpdated_at(OffsetDateTime.now());

        } else if ((userAuthenticationService.isTechnicalSpecialist(authentication) && task.getStatus().equals(TaskStatus.TECHNICAL_REVIEW))
                || (userAuthenticationService.isMachineOperator(authentication) && task.getStatus().equals(TaskStatus.PRODUCTION_REVIEW))) {
            if (!task.getMessage().equals(request.getMessage())) {
                task.setMessage(task.getMessage() + "\n" + request.getMessage());
            }

        } else {
            throw BeltCuttingExceptions.notHavingNecessaryPermissionsForGetTask(request.getId(), beltCuttingUser.getName());
        }
        return new TaskResponseSingle(task);
    }

    @Override
    @Transactional
    public TaskResponseSingle changeTaskStatus(Long id, TaskStatus status, Authentication authentication) {
        BeltCuttingUser beltCuttingUser = userService.getUser(authentication);
        Task task = getTask(id);

        if (status != task.getStatus()) {
            if (userAuthenticationService.isAdmin(authentication)) {
                task.setStatus(status);
                if (task.getStatus().equals(TaskStatus.READY)) {
                    List<Equipment> equipments = task.getEquipmentList();
                    for (Equipment c : equipments) {
                        Piece piece = (Piece) Hibernate.unproxy(c.getPiece());
                        Card card = (Card) Hibernate.unproxy(piece.getCard());
                        piece.setSize(c.getPiece().getSize() - c.getSize());
                        card.setCount(card.getCount() - c.getSize());
                    }
                    task.getCard().setCount(task.getCard().getCount() + task.getCount());
                }
            } else {
                if (userAuthenticationService.isManager(authentication)
                        && userAuthenticationService.getManagerStatuses().contains(task.getStatus())
                        && task.getBeltCuttingUser().equals(beltCuttingUser)) {
                    task.setStatus(status);
                } else if (userAuthenticationService.isTechnicalSpecialist(authentication)
                        && task.getStatus().equals(TaskStatus.TECHNICAL_REVIEW)) {
                    if (task.getEquipmentList().isEmpty() && status == TaskStatus.PRODUCTION_REVIEW) {
                        throw BeltCuttingExceptions.emptyListEquipment(task.getId());
                    }
                    task.setStatus(status);
                } else if (userAuthenticationService.isMachineOperator(authentication) && task.getStatus().equals(TaskStatus.PRODUCTION_REVIEW)) {
                    task.setStatus(status);
                    if (task.getStatus().equals(TaskStatus.READY)) {
                        List<Equipment> equipments = task.getEquipmentList();
                        for (Equipment c : equipments) {
                            Piece piece = (Piece) Hibernate.unproxy(c.getPiece());
                            Card card = (Card) Hibernate.unproxy(piece.getCard());
                            piece.setSize(c.getPiece().getSize() - c.getSize());
                            card.setCount(card.getCount() - c.getSize());
                        }
                        task.getCard().setCount(task.getCard().getCount() + task.getCount());
                    }
                } else {
                    throw BeltCuttingExceptions.notHavingNecessaryPermissionsForGetTask(task.getId(), beltCuttingUser.getName());

                }
            }
        }
        return new TaskResponseSingle(task);
    }

    @Override
    @Transactional
    public TaskResponseSingle create(TaskCreateRequest request, Authentication authentication) {
        BeltCuttingUser beltCuttingUser = userService.getUser(authentication);
        Card card = getCard(request.getCardId());

        Task task = new Task();
        task.setBeltCuttingUser(beltCuttingUser);
        task.setName(request.getName());
        task.setMessage(request.getMessage());
        task.setStatus(TaskStatus.CREATED);
        task.setCount(request.getCount());
        task.setCard(card);
        task.setUpdated_at(OffsetDateTime.now());
        task.setCreated_at(OffsetDateTime.now());

        return new TaskResponseSingle(taskRepository.save(task));
    }

    @Override
    public TaskResponseSingle findById(Long id, Authentication authentication) {
        BeltCuttingUser beltCuttingUser = userService.getUser(authentication);
        Task task = taskRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.taskNotFound(id));
        if (!(userAuthenticationService.isAdmin(authentication)
                || userAuthenticationService.isTechnicalSpecialist(authentication)
                || userAuthenticationService.isMachineOperator(authentication))) {
            if (userAuthenticationService.isManager(authentication)
                    && !beltCuttingUser.equals(task.getBeltCuttingUser())) {
                throw BeltCuttingExceptions.notHavingNecessaryPermissionsForGetTask(id, beltCuttingUser.getName());
            }
        }
        return new TaskResponseSingle(task);
    }

    private Task getTask(long id) {
        return taskRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.taskNotFound(id));
    }

    private Card getCard(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.cardNotFound(id));
    }

}
