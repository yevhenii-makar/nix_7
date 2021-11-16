package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.user.request.SaveUserRequest;
import com.yevheniimakar.beltcutting.model.user.response.UserResponse;

import java.util.List;

public interface UserService {
    void mergeAdmins(List<SaveUserRequest> requests);

    UserResponse create(SaveUserRequest request);
}
