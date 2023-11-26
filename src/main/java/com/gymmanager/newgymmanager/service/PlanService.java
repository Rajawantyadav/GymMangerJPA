package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.GymOwner;
import com.gymmanager.newgymmanager.model.Plan;
import com.gymmanager.newgymmanager.repository.GymOwnerRepo;
import com.gymmanager.newgymmanager.repository.PlanRepo;
import com.gymmanager.newgymmanager.request.PlanReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.GymPlan;
import com.gymmanager.newgymmanager.response.PlanResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService implements PlanInterface {
    @Autowired
    private PlanRepo planRepo;
    @Autowired
    private GymOwnerRepo gymOwnerRepo;


    @Override
    public ResponseEntity<APiResp> addPlan(PlanReq planReq) {
        APiResp resp = new APiResp();
        if (planReq != null) {
            GymOwner gymOwner = gymOwnerRepo.findById(planReq.getOwnerId()).orElseThrow();
            Plan plan = new Plan();
            plan.setPlanAcive("1");
            plan.setGymOwner(gymOwner);
            plan.setPlanName(planReq.getPlanName());
            plan.setPlanDescription(planReq.getPlanDescription());
            plan.setPlanPrice(planReq.getPlanPrice());
            plan.setPlanDuration(planReq.getPlanDuration());
            Plan plan1 = planRepo.save(plan);
            if (plan1 != null) {
                resp.setError("false");
                resp.setMsg("Plan added successfully ..");
                resp.setOwnerId(plan1.getGymOwner().getOwnerId() + "");
                return new ResponseEntity<>(resp, HttpStatus.CREATED);
            } else {
                resp.setError("true");
                resp.setMsg("Some Exception has occurred.. ");
                return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            resp.setError("true");
            resp.setMsg("Some Parameters missing ??.");
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<PlanResp> getPlan(String ownerId) {
        PlanResp resp = new PlanResp();
        try {
            List<Plan> gymPlans = planRepo.findByGymOwnerOwnerId(Long.parseLong(ownerId));
            List<GymPlan> plans = new ArrayList<>();
            if (!CollectionUtils.isEmpty(gymPlans)) {
                for (Plan e : gymPlans) {
                    GymPlan plan = new GymPlan();
                    plan.setPlanName(e.getPlanName());
                    plan.setPlanPrice(e.getPlanPrice());
                    plan.setPlanDuration(e.getPlanDuration());
                    plan.setPlanDescription(e.getPlanDescription());
                    plans.add(plan);
                }
                resp.setError("false");
                resp.setMsg("Plans found..");
                resp.setOwnerId(ownerId);
                resp.setPlans(plans);
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("Plans not found..");
                resp.setOwnerId(ownerId);
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            resp.setError("true");
            resp.setMsg("Exception found..");
            resp.setOwnerId(ownerId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<APiResp> updatePlan(Plan planDetails) {
        APiResp resp = new APiResp();
        if (planDetails != null) {
            Plan plan = planRepo.findById(planDetails.getPlanId()).orElseThrow();
            plan.setPlanDuration(planDetails.getPlanDuration());
            plan.setPlanName(planDetails.getPlanName());
            plan.setPlanDescription(planDetails.getPlanDescription());
            plan.setPlanPrice(planDetails.getPlanPrice());
            Plan plan1 = planRepo.save(plan);
            if (plan1 != null) {
                resp.setError("false");
                resp.setMsg("gym plan updated successfully..");
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("gym plan not found..");
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
