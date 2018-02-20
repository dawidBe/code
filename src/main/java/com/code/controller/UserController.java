package com.code.controller;

import com.code.model.User;
import com.code.request.CreateNewUserRequest;
import com.code.response.GlobalResponse;
import com.code.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/addNewUser", method = RequestMethod.POST)
    public GlobalResponse addUser(@RequestBody CreateNewUserRequest createNewUserRequest){
        return userService.createNewUser(createNewUserRequest);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> getUserList() {
        return userService.getListOfUsers();
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "name") String name) {
        return userService.getUser(name);
    }

    @RequestMapping(value = "/user/{userName}/follow/{followerName}", method = RequestMethod.PUT)
    public GlobalResponse followUser(@PathVariable("userName") String userName,
                                     @PathVariable("followerName") String followerName) {
        return userService.followUser(userName, followerName);
    }

    @RequestMapping(value = "/user/{userName}/unFollow/{followerName}", method = RequestMethod.PUT)
    public GlobalResponse unFollowUser(@PathVariable("userName") String userName,
                                       @PathVariable("followerName") String followerName) {
        return userService.unFollowUser(userName, followerName);
    }

}
