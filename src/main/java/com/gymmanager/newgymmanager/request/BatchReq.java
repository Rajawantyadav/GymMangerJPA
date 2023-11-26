package com.gymmanager.newgymmanager.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchReq {
    private String batchName;
    private String batchStartTime;
    private String batchEndTime;
    @JsonProperty("limit")
    private String batchLimit;
    private long ownerId;
}
