package com.code.service;

import com.code.model.Message;
import com.code.model.TimeLineMessages;
import com.code.request.CreateMessageRequest;
import com.code.response.GlobalResponse;

import java.util.List;

public interface IMessageService {
    GlobalResponse createMessage(CreateMessageRequest createMessageRequest);

    List<Message> getUserMessages(String userName);

    List<TimeLineMessages> getFollowerMessages(String userName);
}
