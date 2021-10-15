package com.yevheniimakar.service;

import com.yevheniimakar.dto.UserDto;

public interface UserService {

    UserDto getUserDtoByPhoneNumber(String phoneNumber);
}
