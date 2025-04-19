package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.model.Plan;
import com.gymmanager.newgymmanager.request.PlanReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.PlanResp;
import com.gymmanager.newgymmanager.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlanController {
    @Autowired
    private PlanService planService;

    @PostMapping("addPlan")
    public ResponseEntity<APiResp> addPlan(@RequestBody PlanReq planReq) {
        return planService.addPlan(planReq);
    }
    @GetMapping("getPlan/{ownerId}")
    public ResponseEntity<PlanResp> getPlan(@PathVariable String ownerId) {
        return planService.getPlan(ownerId);
    }
    @PostMapping("updatePlan")
    public ResponseEntity<APiResp> updatePlan(@RequestBody Plan planDetails) {
        return planService.updatePlan(planDetails);
    }
}
