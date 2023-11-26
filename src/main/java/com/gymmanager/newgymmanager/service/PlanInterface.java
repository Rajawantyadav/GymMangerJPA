package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.Plan;
import com.gymmanager.newgymmanager.request.PlanReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.PlanResp;
import org.springframework.http.ResponseEntity;

public interface PlanInterface {
    ResponseEntity<APiResp> addPlan(PlanReq planDetails);

    ResponseEntity<PlanResp> getPlan(String ownerId);

    ResponseEntity<APiResp> updatePlan(Plan planDetails);
}
