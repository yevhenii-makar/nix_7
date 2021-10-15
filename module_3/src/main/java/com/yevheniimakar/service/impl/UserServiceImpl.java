package com.yevheniimakar.service.impl;

import com.yevheniimakar.dao.UserDao;
import com.yevheniimakar.dao.impl.UserDaoImpl;
import com.yevheniimakar.dto.UserDto;
import com.yevheniimakar.entity.User;
import com.yevheniimakar.service.AccountService;
import com.yevheniimakar.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao;
    AccountService accountService;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
        this.accountService = new AccountServiceImpl();
    }

    @Override
    public UserDto getUserDtoByPhoneNumber(String phoneNumber) {

        return getUserDtoFromUser(userDao.getByPhoneNumber(phoneNumber));

    }

    private UserDto getUserDtoFromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setId(user.getId());

        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAccountDtos(accountService.getAccountDtoListFromAccountList(user.getAccounts()));
        return userDto;
    }
}
