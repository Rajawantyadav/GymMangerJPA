package com.gymmanager.newgymmanager.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
    private String userEmailId;
    private String password;
}
