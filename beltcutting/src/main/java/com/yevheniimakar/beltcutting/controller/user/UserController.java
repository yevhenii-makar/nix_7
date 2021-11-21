package com.yevheniimakar.beltcutting.controller.user;

import com.yevheniimakar.beltcutting.Routes;
import com.yevheniimakar.beltcutting.model.user.request.SaveUserRequest;
import com.yevheniimakar.beltcutting.model.user.response.UserResponse;
import com.yevheniimakar.beltcutting.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.COMPLECTATIONS)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid SaveUserRequest request) {
        return userService.create(request);
    }
}
