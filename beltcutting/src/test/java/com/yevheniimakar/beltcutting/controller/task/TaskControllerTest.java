package com.yevheniimakar.beltcutting.controller.task;

import com.yevheniimakar.beltcutting.model.KnownAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
class TaskControllerTest {

    @Test
    void createTask() {


    }

    @Test
    void getCurrentTask() {
        Set<KnownAuthority> authorities = new HashSet<>();
        authorities.add(KnownAuthority.ROLE_ADMIN);
        Authentication authentication = new UsernamePasswordAuthenticationToken("somemail@somemail.com", null, authorities);


    }

    @Test
    void getTask() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void changeTaskStatus() {
    }

    private URI baseUrl() {
        return URI.create("/messages");
    }

    private URI baseUrl(Long id) {
        return new UriTemplate("/messages/{id}").expand(id);
    }
}