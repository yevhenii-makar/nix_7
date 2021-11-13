package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.exceptions.BeltCuttingExceptions;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.request.TaskCreateRequest;
import com.yevheniimakar.beltcutting.model.task.request.TaskUpdateRequest;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseSingle;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseViewInList;
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

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final ComplectationService complectationService;
    private final UserAuthenticationService userAuthorityService;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, CardRepository cardRepository, ComplectationService complectationService, UserAuthenticationService userAuthorityService, UserAuthenticationService userAuthorityService1) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.complectationService = complectationService;
        this.userAuthorityService = userAuthorityService1;
    }


    @Override
    @Transactional
    public Page<TaskResponseViewInList> list(Pageable pageable) {
        return taskRepository.findAll(pageable).map(TaskResponseViewInList::new);

    }

    @Override
    @Transactional
    public Page<TaskResponseViewInList> getUserTaskList(Pageable pageable, Authentication authentication) {

        List<List> statuses = userAuthorityService.getStatuses(authentication);
        BeltCuttingUser beltCuttingUser = getUser(authentication);
        return taskRepository.findByTaskStatusListAndUser(beltCuttingUser, statuses.get(0), statuses.get(1), pageable).map(o -> new TaskResponseViewInList((Task) o));
    }

    @Override
    public TaskResponseSingle update(Long id, TaskUpdateRequest request, Authentication authentication) {

        BeltCuttingUser beltCuttingUser = getUser(authentication);
        Task task = getTask(id);

        if ((userAuthorityService.isManager(authentication)
                && userAuthorityService.getManagerStatuses().contains(request.getStatus())
                && beltCuttingUser.equals(task.getBeltCuttingUser()))
                || userAuthorityService.isAdmin(authentication)) {
            if (task.getStatus().equals(TaskStatus.CREATED)) {
                task.setName(request.getName());
                task.setMessage(request.getMessage());
            } else {
                task.setMessage(task.getMessage() + "\n" + request.getMessage());
            }
            task.setName(request.getName());
            task.setCount(request.getCount());
            task.setCard(getCard(request.getCardId()));

        } else if ((userAuthorityService.isTechnicalSpecialist(authentication) && task.getStatus().equals(TaskStatus.TECHNICAL_REVIEW))
                || (userAuthorityService.isMachineOperator(authentication) && task.getStatus().equals(TaskStatus.TECHNICAL_REVIEW))) {
            if(!task.getMessage().equals(request.getMessage())){
                task.setMessage(task.getMessage() + "\n" + request.getMessage());
            }
            if(userAuthorityService.isTechnicalSpecialist(authentication)){
                task.setComplectationList(complectationService.saveComplectationList(task.getId(),request.getComplectationRequestList()));
            }
        }

        return new TaskResponseSingle(task);

    }

    @Override
    public TaskResponseSingle changeTaskStatus(Long id, TaskStatus status, Authentication authentication) {
        BeltCuttingUser beltCuttingUser = getUser(authentication);
        Task task = getTask(id);

        if (status != task.getStatus()) {
            if (userAuthorityService.isAdmin(authentication)) {
                task.setStatus(status);
            } else {
                if (userAuthorityService.isManager(authentication)
                        && userAuthorityService.getManagerStatuses().contains(task.getStatus())
                        && beltCuttingUser.equals(task.getBeltCuttingUser())) {
                    task.setStatus(status);
                } else if ((userAuthorityService.isTechnicalSpecialist(authentication) && task.getStatus().equals(TaskStatus.TECHNICAL_REVIEW))
                        || (userAuthorityService.isMachineOperator(authentication) && task.getStatus().equals(TaskStatus.PRODUCTION_REVIEW))) {
                    task.setStatus(status);
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

        return new TaskResponseSingle(taskRepository.save(task));
    }


    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<TaskResponseSingle> findById(Long id, Authentication authentication) {
        BeltCuttingUser beltCuttingUser = getUser(authentication);
        return Optional.empty();
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
