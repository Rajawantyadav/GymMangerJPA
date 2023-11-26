package com.gymmanager.newgymmanager.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APiResp {
    String error;
    String msg;
    String ownerId;
}
