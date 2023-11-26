package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.GymOwner;
import com.gymmanager.newgymmanager.model.Member;
import com.gymmanager.newgymmanager.model.NewBatch;
import com.gymmanager.newgymmanager.model.Plan;
import com.gymmanager.newgymmanager.repository.GymOwnerRepo;
import com.gymmanager.newgymmanager.repository.MemberRepo;
import com.gymmanager.newgymmanager.repository.NewBatchRepo;
import com.gymmanager.newgymmanager.repository.PlanRepo;
import com.gymmanager.newgymmanager.request.MemberReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.GymMember;
import com.gymmanager.newgymmanager.response.MemberResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService implements MemberInterface {
    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private NewBatchRepo newBatchRepo;
    @Autowired
    private PlanRepo planRepo;
    @Autowired
    private GymOwnerRepo gymOwnerRepo;

    @Override
    public ResponseEntity<APiResp> addMember(MemberReq memberReq) {
        APiResp resp = new APiResp();
        if (memberReq != null) {
            List<NewBatch> batchList = newBatchRepo.findByBatchName(memberReq.getMemberBatch());
            NewBatch batch = null;
            if (!CollectionUtils.isEmpty(batchList)) {
                batch = batchList.get(0);
            }
            List<Plan> planList = planRepo.findByPlanName(memberReq.getMemberPlan());
            Plan plan = null;
            if (!CollectionUtils.isEmpty(planList)) {
                plan = planList.get(0);
            }
            GymOwner gymOwner = gymOwnerRepo.findById(memberReq.getOwnerId()).orElseThrow();
            Member member1 = new Member();
            member1.setActive("1");
            member1.setGymOwner(gymOwner);
            member1.setMemberPlan(plan);
            member1.setMemberBatch(batch);
            member1.setMemberName(memberReq.getMemberName());
            member1.setMemberMobile(memberReq.getMemberMobile());
            member1.setMemberEmail(memberReq.getMemberEmail());
            member1.setMemberGender(memberReq.getMemberGender());
            member1.setMemberDob(memberReq.getMemberDob());
            member1.setJoiningDate(LocalDate.now().toString());
            member1.setMemberWeight(memberReq.getMemberWeight());
            //plan expire today date + plan duration in days
            member1.setPlanExpireDate(LocalDate.now().plusDays(Integer.parseInt(plan.getPlanDuration())) + "");
            Member member2 = memberRepo.save(member1);
            if (member2 != null) {
                resp.setError("false");
                resp.setMsg("Member added successfully..");
                return new ResponseEntity<>(resp, HttpStatus.CREATED);
            } else {
                resp.setError("true");
                resp.setMsg("some exception has occurred..");
                return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            resp.setError("true");
            resp.setMsg("Some Parameters missing ??.");
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<MemberResp> getMembers(String ownerId) {
        MemberResp resp = new MemberResp();
        try {
            List<Member> members = memberRepo.findByGymOwnerOwnerId(Long.parseLong(ownerId));
            List<GymMember> gymMembers = new ArrayList<>();
            if (!CollectionUtils.isEmpty(members)) {
                for (Member e : members) {
                    GymMember member = new GymMember();
                    member.setMemberEmail(e.getMemberEmail());
                    member.setMemberName(e.getMemberName());
                    member.setMemberMobile(e.getMemberMobile());
                    member.setMemberDob(e.getMemberDob());
                    member.setMemberGender(e.getMemberGender());
                    member.setJoiningDate(e.getJoiningDate());
                    member.setPlanExpireDate(e.getPlanExpireDate());
                    member.setActivePlanName(e.getMemberPlan().getPlanName());
                    gymMembers.add(member);
                }
                resp.setError("false");
                resp.setMsg("members found..");
                resp.setOwnerId(ownerId);
                resp.setMemebers(gymMembers);
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("members not found..");
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
}
