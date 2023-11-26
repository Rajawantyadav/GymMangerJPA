package com.gymmanager.newgymmanager.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanReq {
    private String planName;
    private String planPrice;
    private String planDuration;
    private String planDescription;
    private long ownerId;
}
