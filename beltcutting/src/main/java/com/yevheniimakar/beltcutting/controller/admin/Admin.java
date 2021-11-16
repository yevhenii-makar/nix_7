package com.yevheniimakar.beltcutting.controller.admin;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.model.user.request.SaveUserRequest;
import com.yevheniimakar.beltcutting.model.user.response.UserResponse;
import com.yevheniimakar.beltcutting.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.ADMIN)
public class Admin {
    private final UserService userService;

    public Admin(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid SaveUserRequest request){
        return userService.create(request);
    }
}
