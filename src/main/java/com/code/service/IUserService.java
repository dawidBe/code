package com.code.service;

import com.code.model.User;
import com.code.request.CreateNewUserRequest;
import com.code.response.GlobalResponse;

import java.util.List;

public interface IUserService {

    List<User> getListOfUsers();

    GlobalResponse createNewUser(CreateNewUserRequest createNewUserRequest);

    User getUser(String name);

    GlobalResponse followUser(String userName, String followedName);

    GlobalResponse unFollowUser(String userName, String followedName);
}
