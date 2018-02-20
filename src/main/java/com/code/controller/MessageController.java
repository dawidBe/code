package com.code.controller;

import com.code.model.Message;
import com.code.model.TimeLineMessages;
import com.code.request.CreateMessageRequest;
import com.code.response.GlobalResponse;
import com.code.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MessageController {

    private IMessageService messageService;

    @Autowired
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/message/addMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GlobalResponse addMessage(@Valid @RequestBody CreateMessageRequest createMessageRequest){
        return messageService.createMessage(createMessageRequest);
    }

    @RequestMapping(value = "/message/{userName}", method = RequestMethod.GET)
    public List<Message> getMessages(@PathVariable("userName") String userName){
        return messageService.getUserMessages(userName);
    }

    @RequestMapping(value = "/message/{userName}/follower", method = RequestMethod.GET)
    public List<TimeLineMessages> getFollowersMessages(@PathVariable("userName") String userName){
        return messageService.getFollowerMessages(userName);
    }
}
