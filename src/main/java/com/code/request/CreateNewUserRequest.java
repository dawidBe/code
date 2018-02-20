package com.code.request;

import java.io.Serializable;

public class CreateNewUserRequest implements Serializable {

    private static final long serialVersionUID = -1234584718350326919L;


    private String name;

    public CreateNewUserRequest() {
    }

    public CreateNewUserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
