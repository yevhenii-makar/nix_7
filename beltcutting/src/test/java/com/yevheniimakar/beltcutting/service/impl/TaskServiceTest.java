package com.yevheniimakar.beltcutting.service.impl;

import com.yevheniimakar.beltcutting.model.KnownAuthority;
import com.yevheniimakar.beltcutting.model.task.Task;
import com.yevheniimakar.beltcutting.model.task.TaskStatus;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseSingle;
import com.yevheniimakar.beltcutting.model.task.response.TaskResponseViewInList;
import com.yevheniimakar.beltcutting.repository.CardRepository;
import com.yevheniimakar.beltcutting.repository.ComplectationRepository;
import com.yevheniimakar.beltcutting.repository.TaskRepository;
import com.yevheniimakar.beltcutting.repository.UserRepository;
import com.yevheniimakar.beltcutting.service.TaskService;
import com.yevheniimakar.beltcutting.service.UnitService;
import com.yevheniimakar.beltcutting.service.UserAuthenticationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles({"local-test", "debug"})
class TaskServiceTest {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UnitService unitService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private ComplectationRepository complectationRepository;
    @Autowired
    private CardRepository cardRepository;

    private final Pageable pageable = PageRequest.of(0, 20);

    @BeforeEach
    public void setUpDb() {
        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("/test-create-data.sql"));

        DatabasePopulatorUtils.execute(tables, dataSource);

    }

    @Test
    @Transactional
    void getUserTaskList() {

        List<Task> controlTaskList = taskRepository.findAll();

        List<TaskResponseViewInList> controlAdminTaskList = controlTaskList.stream().filter(o -> o.getStatus() != TaskStatus.END).map(TaskResponseViewInList::new).collect(Collectors.toList());

        List<TaskResponseViewInList> adminsTaskList = taskService.getUserTaskList(pageable, getAuthenticationAdmin()).toList();

        assertEquals(controlAdminTaskList.size(), adminsTaskList.size());
        assertTrue(controlAdminTaskList.containsAll(adminsTaskList));
        assertTrue(adminsTaskList.containsAll(controlAdminTaskList));

        List<TaskResponseViewInList> controlManagerTaskList = controlTaskList.stream()
                .filter(o ->
                        o.getBeltCuttingUser().getEmail().equals(getAuthenticationManager().getPrincipal())
                                && userAuthenticationService.getManagerStatuses().contains(o.getStatus())
                ).map(TaskResponseViewInList::new).collect(Collectors.toList());

        List<TaskResponseViewInList> managerTaskList = taskService.getUserTaskList(pageable, getAuthenticationManager()).toList();

        assertEquals(controlManagerTaskList.size(), managerTaskList.size());
        assertTrue(controlManagerTaskList.containsAll(managerTaskList));
        assertTrue(managerTaskList.containsAll(controlManagerTaskList));

        List<TaskResponseViewInList> controlManagerAndTechTaskList = controlTaskList.stream()
                .filter(o ->
                        ((o.getBeltCuttingUser().getEmail().equals(getAuthenticationManagerAndTech().getPrincipal())
                                && userAuthenticationService.getManagerStatuses().contains(o.getStatus()))
                                || (userAuthenticationService.getTechStatuses(getAuthenticationManagerAndTech()).contains(o.getStatus()))))
                .map(TaskResponseViewInList::new).collect(Collectors.toList());

        List<TaskResponseViewInList> managerAndTechTaskList = taskService.getUserTaskList(pageable, getAuthenticationManagerAndTech()).toList();

        assertEquals(controlManagerAndTechTaskList.size(), managerAndTechTaskList.size());
        assertTrue(controlManagerAndTechTaskList.containsAll(managerAndTechTaskList));
        assertTrue(managerAndTechTaskList.containsAll(controlManagerAndTechTaskList));

        List<TaskResponseViewInList> controlTechTaskList = controlTaskList.stream()
                .filter(o ->
                        ((userAuthenticationService.getTechStatuses(getAuthenticationTech()).contains(o.getStatus()))))
                .map(TaskResponseViewInList::new).collect(Collectors.toList());

        List<TaskResponseViewInList> techTaskList = taskService.getUserTaskList(pageable, getAuthenticationTech()).toList();

        assertEquals(controlTechTaskList.size(), techTaskList.size());
        assertTrue(controlTechTaskList.containsAll(techTaskList));
        assertTrue(techTaskList.containsAll(controlTechTaskList));

        List<TaskResponseViewInList> controlOperatorTaskList = controlTaskList.stream()
                .filter(o ->
                        ((userAuthenticationService.getTechStatuses(getAuthenticationOperator()).contains(o.getStatus()))))
                .map(TaskResponseViewInList::new).collect(Collectors.toList());

        List<TaskResponseViewInList> operatorTaskList = taskService.getUserTaskList(pageable, getAuthenticationOperator()).toList();

        assertEquals(controlOperatorTaskList.size(), operatorTaskList.size());
        assertTrue(controlOperatorTaskList.containsAll(operatorTaskList));
        assertTrue(operatorTaskList.containsAll(controlOperatorTaskList));

    }

    @Test
    void changeTaskStatus() {
//        List<Task> controlTaskList = taskRepository.findAll();
        List<Task> controlTaskList = new ArrayList<>(taskRepository.getTaskList());
        Task task = controlTaskList.stream().filter(o -> o.getStatus() == TaskStatus.CREATED).findFirst().get();
        TaskStatus status1 = TaskStatus.TECHNICAL_REVIEW;

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> taskService.changeTaskStatus(task.getId(), status1, getAuthenticationManagerAndTech()))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.FORBIDDEN));

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> taskService.changeTaskStatus(task.getId(), status1, getAuthenticationTech()))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.FORBIDDEN));

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> taskService.changeTaskStatus(task.getId(), status1, getAuthenticationOperator()))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.FORBIDDEN));

        TaskResponseSingle taskResponse = taskService.changeTaskStatus(task.getId(), status1, getAuthenticationManager());

        assertTrue(taskResponse.getStatus() == status1 && taskResponse.getId() == task.getId());

        TaskStatus status2 = TaskStatus.PRODUCTION_REVIEW;

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> taskService.changeTaskStatus(task.getId(), status2, getAuthenticationManagerAndTech()))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.CONFLICT));

        Task task2 = controlTaskList.stream().filter(o -> o.getStatus() == TaskStatus.TECHNICAL_REVIEW).findFirst().get();

        TaskResponseSingle taskResponse2 = taskService.changeTaskStatus(task2.getId(), status2, getAuthenticationTech());

        assertTrue(taskResponse2.getStatus() == status2 && taskResponse2.getId() == task2.getId());

        Task task3 = controlTaskList.stream().filter(o -> o.getStatus() == TaskStatus.PRODUCTION_REVIEW).findFirst().get();
        TaskStatus status3 = TaskStatus.READY;
        int actualCountOnCard = task3.getCard().getCount();
        int actualCountOnAccessoryCard = task3.getComplectationList().stream().map(o -> o.getCard()).distinct().map(o -> o.getCount()).reduce(0, (subtotal, element) -> subtotal + element);

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> taskService.changeTaskStatus(task3.getId(), status3, getAuthenticationManagerAndTech()))
                .satisfies(e -> assertThat(e.getStatus()).isSameAs(HttpStatus.FORBIDDEN));
        TaskResponseSingle taskResponseSingle = taskService.changeTaskStatus(task3.getId(), status3, getAuthenticationOperator());
        Task result = taskRepository.getTaskList().stream().filter(o -> o.getId() == taskResponseSingle.getId()).findFirst().get();
        int resultCountOnCard = result.getCard().getCount();
        int resultCountOnAccessoryCard = result.getComplectationList().stream().map(o -> o.getCard()).distinct().map(o -> o.getCount()).reduce(0, (subtotal, element) -> subtotal + element);
        assertTrue(actualCountOnCard < resultCountOnCard);
        assertTrue(actualCountOnAccessoryCard > resultCountOnAccessoryCard);

    }

    private Authentication getAuthenticationAdmin() {
        Set<KnownAuthority> authorities = new HashSet<>();
        authorities.add(KnownAuthority.ROLE_ADMIN);
        return new UsernamePasswordAuthenticationToken("somemail@somemail.com", null, authorities);
    }

    private Authentication getAuthenticationManager() {
        Set<KnownAuthority> authorities = new HashSet<>();
        authorities.add(KnownAuthority.ROLE_MANAGER);
        return new UsernamePasswordAuthenticationToken("manager1@mail.com", null, authorities);
    }

    private Authentication getAuthenticationManagerAndTech() {
        Set<KnownAuthority> authorities = new HashSet<>();
        authorities.add(KnownAuthority.ROLE_MANAGER);
        authorities.add(KnownAuthority.ROLE_TECHNICAL_SPECIALIST);
        return new UsernamePasswordAuthenticationToken("manager2@mail.com", null, authorities);
    }

    private Authentication getAuthenticationOperator() {
        Set<KnownAuthority> authorities = new HashSet<>();
        authorities.add(KnownAuthority.ROLE_MACHINE_OPERATOR);
        return new UsernamePasswordAuthenticationToken("operator@mail.com", null, authorities);
    }

    private Authentication getAuthenticationTech() {
        Set<KnownAuthority> authorities = new HashSet<>();
        authorities.add(KnownAuthority.ROLE_TECHNICAL_SPECIALIST);
        return new UsernamePasswordAuthenticationToken("tech@mail.com", null, authorities);
    }

    @AfterEach
    public void afterAll() {

        ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("/test-delete-data.sql"));

        DatabasePopulatorUtils.execute(tables, dataSource);
    }
}