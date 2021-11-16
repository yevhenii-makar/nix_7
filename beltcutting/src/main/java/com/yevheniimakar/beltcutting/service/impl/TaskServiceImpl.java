package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.complectation.Complectation;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.request.TaskCreateRequest;
import com.yevheniimakar.beltcutting.model.task.request.TaskUpdateRequest;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseSingle;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseViewInList;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import com.yevheniimakar.beltcutting.repository.CardRepository;
import com.yevheniimakar.beltcutting.repository.TaskRepository;
import com.yevheniimakar.beltcutting.repository.UserRepository;
import com.yevheniimakar.beltcutting.service.ComplectationService;
import com.yevheniimakar.beltcutting.service.TaskService;
import com.yevheniimakar.beltcutting.service.UserAuthenticationService;
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
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final ComplectationService complectationService;
    private final UserAuthenticationService userAuthenticationService;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, CardRepository cardRepository, ComplectationService complectationService, UserAuthenticationService userAuthenticationService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.complectationService = complectationService;
        this.userAuthenticationService = userAuthenticationService;
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
        BeltCuttingUser beltCuttingUser = getUser(authentication);
        return taskRepository.findByTaskStatusListAndUser(beltCuttingUser, statuses.get(0), statuses.get(1), pageable).map(o -> new TaskResponseViewInList((Task) o));
    }

    @Override
    public TaskResponseSingle update(Long id, TaskUpdateRequest request, Authentication authentication) {

        BeltCuttingUser beltCuttingUser = getUser(authentication);
        Task task = getTask(id);

        if ((userAuthenticationService.isManager(authentication)
                && userAuthenticationService.getManagerStatuses().contains(request.getStatus())
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
            if (userAuthenticationService.isTechnicalSpecialist(authentication)) {
                task.setComplectationList(complectationService.saveComplectationList(task.getId(), request.getComplectationRequestList()));
            }
        } else {
            throw BeltCuttingExceptions.notHavingNecessaryPermissionsForGetTask(request.getId(), beltCuttingUser.getName());
        }
        return new TaskResponseSingle(task);
    }

    @Override
    @Transactional
    public TaskResponseSingle changeTaskStatus(Long id, TaskStatus status, Authentication authentication) {
        BeltCuttingUser beltCuttingUser = getUser(authentication);
        Task task = getTask(id);

        if (status != task.getStatus()) {
            if (userAuthenticationService.isAdmin(authentication)) {
                task.setStatus(status);
            } else {
                if (userAuthenticationService.isManager(authentication)
                        && userAuthenticationService.getManagerStatuses().contains(task.getStatus())
                        && beltCuttingUser.equals(task.getBeltCuttingUser())) {
                    task.setStatus(status);
                } else if (userAuthenticationService.isTechnicalSpecialist(authentication) && task.getStatus().equals(TaskStatus.TECHNICAL_REVIEW)) {
                    task.setStatus(status);
                } else if (userAuthenticationService.isMachineOperator(authentication) && task.getStatus().equals(TaskStatus.PRODUCTION_REVIEW)) {
                    task.setStatus(status);
                    if (task.getStatus().equals(TaskStatus.READY)) {
                        List<Complectation> complectations = task.getComplectationList();
                        for (Complectation c : complectations) {
                            c.getPiece().setSize(c.getPiece().getSize() - c.getSize());
                            c.getPiece().getCard().setCount(c.getPiece().getCard().getCount() - c.getSize());
                        }
                        task.getCard().setCount(task.getCount());
                    }
                }
            }
        }
        return new TaskResponseSingle(task);
    }

    @Override
    public TaskResponseSingle create(TaskCreateRequest request, Authentication authentication) {
        BeltCuttingUser beltCuttingUser = getUser(authentication);
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
    public void deleteById(Long id) {

    }

    @Override
    public TaskResponseSingle findById(Long id, Authentication authentication) {
        BeltCuttingUser beltCuttingUser = getUser(authentication);
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

    private BeltCuttingUser getUser(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> BeltCuttingExceptions.userNotFound(email));
    }

    private Card getCard(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> BeltCuttingExceptions.cardNotFound(id));
    }

}
