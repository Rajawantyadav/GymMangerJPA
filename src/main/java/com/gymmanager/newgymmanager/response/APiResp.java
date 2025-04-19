package com.gymmanager.newgymmanager.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APiResp {
    private String error;
    private String msg;
    private String ownerId;
    private String token;
}
