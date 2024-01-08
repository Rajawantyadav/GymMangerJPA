package com.gymmanager.newgymmanager.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymBatch {
    private String batchId;
    private String batchName;
    private String batchStartTime;
    private String batchEndTime;
    @JsonProperty("limit")
    private String batchLimit;

}
