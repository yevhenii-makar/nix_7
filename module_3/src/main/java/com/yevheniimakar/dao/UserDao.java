package com.yevheniimakar.dao;

import com.yevheniimakar.entity.User;

public interface UserDao {

    User getByPhoneNumber(String phoneNumber);
}
