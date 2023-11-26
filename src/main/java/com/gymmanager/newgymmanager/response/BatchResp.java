package com.gymmanager.newgymmanager.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatchResp {
    private String error;
    private String msg;
    private List<GymBatch> batches;
    private String ownerId;
}
