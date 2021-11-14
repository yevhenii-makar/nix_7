package com.yevheniimakar.beltcutting.service;

import com.yevheniimakar.beltcutting.model.user.request.SaveUserRequest;

import java.util.List;

public interface UserService {
    void mergeAdmins(List<SaveUserRequest> requests);
}
