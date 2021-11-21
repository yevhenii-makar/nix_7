package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import com.yevheniimakar.beltcutting.model.user.request.SaveUserRequest;
import com.yevheniimakar.beltcutting.model.user.response.UserResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void mergeAdmins(List<SaveUserRequest> requests);

    UserResponse create(SaveUserRequest request);

    BeltCuttingUser getUser(Authentication authentication);
}
