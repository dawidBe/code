package com.code.request;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CreateMessageRequest implements Serializable {

    private static final long serialVersionUID = -597858423462326919L;

    @NotEmpty(message = "Cannot be empty")
    @Size(max = 140, message = "Max size is 140")
    @Valid
    private String message;
    private String userName;

    public CreateMessageRequest() {
    }

    public CreateMessageRequest(String message, String userName) {
        this.message = message;
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
