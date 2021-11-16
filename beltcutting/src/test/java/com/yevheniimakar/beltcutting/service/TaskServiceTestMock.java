package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.Manufacturer;
import com.yevheniimakar.beltcutting.model.card.Card;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.request.TaskUpdateRequest;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseSingle;
import com.yevheniimakar.beltcutting.model.unit.Unit;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import com.yevheniimakar.beltcutting.repository.CardRepository;
import com.yevheniimakar.beltcutting.repository.TaskRepository;
import com.yevheniimakar.beltcutting.repository.UserRepository;
import com.yevheniimakar.beltcutting.service.impl.ComplectationServiceImpl;
import com.yevheniimakar.beltcutting.service.impl.TaskServiceImpl;
import com.yevheniimakar.beltcutting.service.impl.UserAuthenticationServiceImpl;
import com.yevheniimakar.beltcutting.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;


@ActiveProfiles({"local-test", "debug"})
public class TaskServiceTestMock{

    long absentId;
    long presentId1;
    long presentId2;
    String massage1;
    String massage2;
    String emailManager1;
    String emailManager2;
    String emailAdmin;
    String emailTech;
    String emailOperator;
    BeltCuttingUser userManager1;
    BeltCuttingUser userManager2;
    BeltCuttingUser userAdmin;
    BeltCuttingUser userTech;
    BeltCuttingUser userOperator;
    Card card;
    Task task1;
    Task task2;
    Authentication authenticationManager1;
    Authentication authenticationManager2;
    Authentication authenticationAdmin;
    Authentication authenticationTech;
    Authentication authenticationOperator;
    private TaskServiceImpl taskService;
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private UserServiceImpl userService;
    private CardRepository cardRepository;
    private ComplectationService complectationService;
    private UserAuthenticationService userAuthenticationService;

    private static void assertTaskMatchesResponse(Task task, TaskResponseSingle taskResponseSingle) {
        assertThat(taskResponseSingle.getId()).isEqualTo(task.getId());
        assertThat(taskResponseSingle.getName()).isEqualTo(task.getName());
        assertThat(taskResponseSingle.getMessage()).isEqualTo(task.getMessage());
        assertThat(taskResponseSingle.getStatus()).isEqualTo(task.getStatus());

    }

    private static void assertTaskMachesRespone(TaskUpdateRequest request, TaskResponseSingle response) {
        assertThat(request.getId()).isEqualTo(response.getId());
        assertThat(request.getName()).isEqualTo(response.getName());
        assertThat(request.getCardId()).isEqualTo(response.getCard().getId());
        assertThat(request.getCount()).isEqualTo(response.getCount());
    }

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        userRepository = mock(UserRepository.class);
        userService = mock(UserServiceImpl.class);
        cardRepository = mock(CardRepository.class);
        complectationService = mock(ComplectationServiceImpl.class);
        userAuthenticationService = mock(UserAuthenticationServiceImpl.class);
        taskService = new TaskServiceImpl(taskRepository, userRepository, cardRepository, complectationService, userAuthenticationService);

        absentId = 1L;
        presentId1 = 2L;
        presentId2 = 3L;

        massage1 = "1 task";
        massage2 = "2 task";

        emailManager1 = "some@some.com";
        emailManager2 = "some2@some.com";
        emailAdmin = "someAdmin@some.com";
        emailTech = "someTech@some.com";
        emailOperator = "someOperator@some.com";

        userManager1 = new BeltCuttingUser();
        userManager1.setId(1L);
        userManager1.setName("user1");
        userManager1.setEmail(emailManager1);

        userManager2 = new BeltCuttingUser();
        userManager2.setId(2L);
        userManager2.setName("user2");
        userManager2.setEmail(emailManager2);

        userAdmin = new BeltCuttingUser();
        userAdmin.setId(3L);
        userAdmin.setName("admin");
        userAdmin.setEmail(emailAdmin);

        userTech = new BeltCuttingUser();
        userAdmin.setId(4L);
        userAdmin.setName("tech");
        userAdmin.setEmail(emailTech);

        userOperator = new BeltCuttingUser();
        userAdmin.setId(5L);
        userAdmin.setName("operator");
        userAdmin.setEmail(emailTech);

        card = new Card(1L, "testCard", 5, 10L, 5, new Manufacturer(), null, new Unit());

        task1 = new Task(presentId1, "test1", massage1, null, null, TaskStatus.CREATED, userManager1, card, null, new ArrayList<>());
        task2 = new Task(presentId2, "test1", massage1, null, null, TaskStatus.CREATED, userManager1, card, null, new ArrayList<>());

        authenticationManager1 = new UsernamePasswordAuthenticationToken(emailManager1, "manager1");
        authenticationManager2 = new UsernamePasswordAuthenticationToken(emailManager2, "manager2");
        authenticationAdmin = new UsernamePasswordAuthenticationToken(emailAdmin, "admin");
        authenticationTech = new UsernamePasswordAuthenticationToken(emailAdmin, "tech");
        authenticationOperator = new UsernamePasswordAuthenticationToken(emailAdmin, "operator");

    }

    @Test
    void getTaskById() {

        when(taskRepository.findById(absentId)).thenReturn(Optional.empty());
        when(taskRepository.findById(presentId1)).thenReturn(Optional.of(task1));

        when(userRepository.findByEmail(emailManager1)).thenReturn(Optional.of(userManager1));
        when(userRepository.findByEmail(emailManager2)).thenReturn(Optional.of(userManager2));
        when(userRepository.findByEmail(emailAdmin)).thenReturn(Optional.of(userAdmin));

        when(userAuthenticationService.isManager(authenticationManager1)).thenReturn(true);
        when(userAuthenticationService.isManager(authenticationManager2)).thenReturn(true);
        when(userAuthenticationService.isAdmin(authenticationAdmin)).thenReturn(true);

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> taskService.findById(absentId, authenticationManager1))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.NOT_FOUND));
        verify(taskRepository).findById(absentId);

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> taskService.findById(presentId1, authenticationManager2))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.FORBIDDEN));
        verify(taskRepository).findById(presentId1);

        Optional<TaskResponseSingle> presentResponse1 = Optional.of(taskService.findById(presentId1, authenticationManager1));

        assertThat(presentResponse1).hasValueSatisfying(tr ->
                assertTaskMatchesResponse(task1, tr));
        verify(taskRepository, times(2)).findById(presentId1);

        Optional<TaskResponseSingle> presentResponse2 = Optional.of(taskService.findById(presentId1, authenticationAdmin));

        assertThat(presentResponse2).hasValueSatisfying(tr ->
                assertTaskMatchesResponse(task1, tr));
        verify(taskRepository, times(3)).findById(presentId1);

    }

    @Test
    public void update() {

        TaskUpdateRequest request = new TaskUpdateRequest();
        request.setId(presentId1);
        request.setName("task1");
        request.setCardId(1L);
        request.setCount(3);
        request.setStatus(TaskStatus.CREATED);
        request.setMessage(massage1);
        request.setUserId(userManager1.getId());

        List<TaskStatus> managerStatuses = Arrays.asList(TaskStatus.CREATED, TaskStatus.READY, TaskStatus.CLARIFICATION_REQUEST, TaskStatus.CANCELED);
        List<TaskStatus> adminStatuses = Arrays.asList(TaskStatus.TECHNICAL_REVIEW, TaskStatus.PRODUCTION_REVIEW, TaskStatus.CREATED, TaskStatus.READY, TaskStatus.CLARIFICATION_REQUEST, TaskStatus.CANCELED);

        when(userRepository.findByEmail(emailManager1)).thenReturn(Optional.of(userManager1));
        when(userRepository.findByEmail(emailAdmin)).thenReturn(Optional.of(userAdmin));
        when(userRepository.findByEmail(emailTech)).thenReturn(Optional.of(userTech));
        when(userRepository.findByEmail(emailOperator)).thenReturn(Optional.of(userOperator));

        when(userAuthenticationService.isManager(authenticationManager1)).thenReturn(true);
        when(userAuthenticationService.isManager(authenticationManager2)).thenReturn(true);
        when(userAuthenticationService.isAdmin(authenticationAdmin)).thenReturn(true);
        when(userAuthenticationService.isTechnicalSpecialist(authenticationTech)).thenReturn(true);
        when(userAuthenticationService.isMachineOperator(authenticationOperator)).thenReturn(true);

        when(userAuthenticationService.getManagerStatuses()).thenReturn(managerStatuses);
        when(userAuthenticationService.getAdminStatuses()).thenReturn(adminStatuses);
        when(userAuthenticationService.getTechStatuses(authenticationTech)).thenReturn(Arrays.asList(TaskStatus.TECHNICAL_REVIEW));
        when(userAuthenticationService.getTechStatuses(authenticationOperator)).thenReturn(Arrays.asList(TaskStatus.PRODUCTION_REVIEW));

        when(cardRepository.findById(card.getId())).thenReturn(Optional.of(card));

        when(taskRepository.findById(presentId1)).thenReturn(Optional.of(task1));
        when(taskRepository.findById(presentId2)).thenReturn(Optional.of(task2));

        Optional<TaskResponseSingle> responseManager = Optional.of(taskService.update(presentId1, request, authenticationManager1));

        assertThat(responseManager).hasValueSatisfying(rm ->
                assertTaskMachesRespone(request, rm));
        verify(taskRepository).findById(presentId1);

        request.setMessage(massage2);
        request.setStatus(TaskStatus.CLARIFICATION_REQUEST);
        task1.setStatus(TaskStatus.CLARIFICATION_REQUEST);
        when(taskRepository.findById(presentId1)).thenReturn(Optional.of(task1));
        Optional<TaskResponseSingle> responseManagerClarificationRequest = Optional.of(taskService.update(presentId1, request, authenticationManager1));

        assertThat(responseManagerClarificationRequest).hasValueSatisfying(rm ->
                assertTaskMachesRespone(request, rm));
        assertThat(responseManagerClarificationRequest.get().getMessage()).isEqualTo(massage1 + "\n" + massage2);
        verify(taskRepository, times(2)).findById(presentId1);

        request.setMessage(massage2);
        task1.setMessage(massage1);

        request.setStatus(TaskStatus.TECHNICAL_REVIEW);
        task1.setStatus(TaskStatus.TECHNICAL_REVIEW);

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> taskService.update(presentId1, request, authenticationManager1))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.FORBIDDEN));
        verify(taskRepository, times(3)).findById(presentId1);


        when(taskRepository.findById(presentId1)).thenReturn(Optional.of(task1));
        Optional<TaskResponseSingle> responseTechnicalReview = Optional.of(taskService.update(presentId1, request, authenticationTech));

        assertThat(responseTechnicalReview).hasValueSatisfying(rm ->
                assertTaskMachesRespone(request, rm));
        assertThat(responseTechnicalReview.get().getMessage()).isEqualTo(massage1 + "\n" + massage2);
        verify(taskRepository, times(4)).findById(presentId1);


        request.setMessage(massage2);
        task1.setMessage(massage1);

        request.setStatus(TaskStatus.PRODUCTION_REVIEW);
        task1.setStatus(TaskStatus.PRODUCTION_REVIEW);

        when(taskRepository.findById(presentId1)).thenReturn(Optional.of(task1));
        Optional<TaskResponseSingle> responseProductionReview = Optional.of(taskService.update(presentId1, request, authenticationOperator));

        assertThat(responseProductionReview).hasValueSatisfying(rm ->
                assertTaskMachesRespone(request, rm));
        assertThat(responseTechnicalReview.get().getMessage()).isEqualTo(massage1 + "\n" + massage2);
        verify(taskRepository, times(5)).findById(presentId1);

    }

}
