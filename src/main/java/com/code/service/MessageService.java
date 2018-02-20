package com.code.service;

import com.code.model.*;
import com.code.request.CreateMessageRequest;
import com.code.request.CreateNewUserRequest;
import com.code.response.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageService implements IMessageService {

    private IUserService userService;

    @Autowired
    public MessageService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public GlobalResponse createMessage(CreateMessageRequest createMessageRequest) {
        if (StringUtils.hasLength(createMessageRequest.getMessage())
                && createMessageRequest.getMessage().length() > 140) {
            return new GlobalResponse(ResultCode.FALSE, ResultReason.TOO_LARGE_MESSAGE);
        }

        User user = userService.getUser(createMessageRequest.getUserName());

        if (user == null) {
            return userService.createNewUser(new CreateNewUserRequest(createMessageRequest.getUserName()));
        }

        boolean isAddedMessage = user.getMessages().add(new Message(createMessageRequest.getMessage(), new Date()));

        if (isAddedMessage) {
            return new GlobalResponse(ResultCode.OK);
        }
        return new GlobalResponse(ResultCode.FALSE, ResultReason.UNEXPECTED_ERROR);
    }

    @Override
    public List<Message> getUserMessages(String userName) {
        User user = userService.getUser(userName);

        if (user == null) {
            return Collections.emptyList();
        }

        return user.getMessages()
                .stream()
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeLineMessages> getFollowerMessages(String userName) {
        User user = userService.getUser(userName);

        if (user == null) {
            return Collections.emptyList();
        }
        List<TimeLineMessages> allMessages = new ArrayList<>();

        user.getFollowers().forEach
                (u -> u.getMessages()
                        .forEach(m -> allMessages.add(new TimeLineMessages(u.getName(), m.getMessage(), m.getDate()))));

        if (allMessages.isEmpty()) {
            return Collections.emptyList();
        }

        return allMessages
                .stream()
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .collect(Collectors.toList());
    }
}
