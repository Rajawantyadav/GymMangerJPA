package com.gymmanager.newgymmanager.response;

import com.gymmanager.newgymmanager.request.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanResp {
    private String error;
    private String msg;
    private List<GymPlan> plans;
    private String ownerId;
}
