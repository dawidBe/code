package com.code.service;

import com.code.model.ResultCode;
import com.code.model.ResultReason;
import com.code.model.User;
import com.code.request.CreateNewUserRequest;
import com.code.response.GlobalResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class UserService implements IUserService {

    private List<User> users = new ArrayList<>();

    @Override
    public List<User> getListOfUsers() {
        return users;
    }

    @Override
    public GlobalResponse createNewUser(CreateNewUserRequest createNewUserRequest) {
        if (userEgist(createNewUserRequest.getName())) {
            return new GlobalResponse(ResultCode.FALSE, ResultReason.USER_EXIST);
        }

        int randomId;
        do {
            randomId = new Random().nextInt();
        } while (isUnique(randomId));

        if (users.add(new User(randomId, createNewUserRequest.getName()))) {
            return new GlobalResponse(ResultCode.OK);
        }

        return new GlobalResponse(ResultCode.FALSE, ResultReason.UNEXPECTED_ERROR);
    }

    private boolean userEgist(String name) {
        return users.stream().anyMatch(u -> u.getName().equals(name));
    }

    private boolean isUnique(int randomId) {
        return users.stream().anyMatch(u -> u.getId() == randomId);
    }

    @Override
    public User getUser(String name){
        Optional<User> optionalUser = users.stream().filter(x -> x.getName().equals(name)).findFirst();
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public GlobalResponse followUser(String userName, String followedName) {
        User user = getUser(userName);
        User followed = getUser(followedName);

        if (user == null || followed == null) {
            return new GlobalResponse(ResultCode.FALSE, ResultReason.USER_NOT_EXIST);
        }

        if (user.getFollowers().contains(followed)) {
            return new GlobalResponse(ResultCode.OK);
        }

        if (addFolower(user, followed)) {
            return new GlobalResponse(ResultCode.OK);
        }
        return new GlobalResponse(ResultCode.FALSE, ResultReason.UNEXPECTED_ERROR);
    }

    private boolean addFolower(User user, User followed) {
        return user.getFollowers().add(followed);
    }

    @Override
    public GlobalResponse unFollowUser(String userName, String followedName) {
        User user = getUser(userName);
        User followed = getUser(followedName);

        if (user == null || followed == null) {
            return new GlobalResponse(ResultCode.FALSE, ResultReason.USER_NOT_EXIST);
        }

        if (removeFolower(user, followed)) {
            return new GlobalResponse(ResultCode.OK);
        }
        return new GlobalResponse(ResultCode.FALSE, ResultReason.UNEXPECTED_ERROR);
    }

    private boolean removeFolower(User user, User followed) {
        return user.getFollowers().remove(followed);
    }
}
