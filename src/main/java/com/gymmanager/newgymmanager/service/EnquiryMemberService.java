package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.EnquiryMember;
import com.gymmanager.newgymmanager.model.GymOwner;
import com.gymmanager.newgymmanager.repository.EnquiryMemberRepo;
import com.gymmanager.newgymmanager.repository.GymOwnerRepo;
import com.gymmanager.newgymmanager.request.EnquiryMemberReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.EnquiryResp;
import com.gymmanager.newgymmanager.response.GymEnquiryMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnquiryMemberService implements EnquiryMemberInterface {
    @Autowired
    private EnquiryMemberRepo enquiryMemberRepo;
    @Autowired
    private GymOwnerRepo gymOwnerRepo;


    @Override
    public ResponseEntity<APiResp> addEnquiryMember(EnquiryMemberReq enquiryMemberReq) {
        APiResp resp = new APiResp();
        if (enquiryMemberReq != null) {
            EnquiryMember member = new EnquiryMember();
            GymOwner gymOwner = gymOwnerRepo.findById(enquiryMemberReq.getOwnerId()).orElseThrow();
            member.setGymOwner(gymOwner);
            member.setMemberEmail(enquiryMemberReq.getMember_email());
            member.setMemberName(enquiryMemberReq.getMember_name());
            member.setMemberMobile(enquiryMemberReq.getMember_mobile());
            member.setEnquiryDate(enquiryMemberReq.getEnquiry_date());
            member.setEnquiryDesc(enquiryMemberReq.getEnquiry_desc());
            member.setMemberGender(enquiryMemberReq.getMember_gender());
            EnquiryMember enquiryMember = enquiryMemberRepo.save(member);
            if (enquiryMember != null) {
                resp.setError("false");
                resp.setMsg("enquiryMemberDetails added successfully ..");
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
    public ResponseEntity<APiResp> updateEnquiryMember(EnquiryMember enquiryMemberDetails) {
        APiResp resp = new APiResp();
        if (enquiryMemberDetails != null) {
            EnquiryMember enquiryMember = enquiryMemberRepo.findById(enquiryMemberDetails.getMemberId()).orElseThrow();
            enquiryMember.setEnquiryDesc(enquiryMemberDetails.getEnquiryDesc());
            enquiryMember.setEnquiryDate(enquiryMemberDetails.getEnquiryDate());
            enquiryMember.setMemberEmail(enquiryMemberDetails.getMemberEmail());
            enquiryMember.setMemberGender(enquiryMemberDetails.getMemberGender());
            enquiryMember.setMemberMobile(enquiryMemberDetails.getMemberMobile());
            EnquiryMember enquiryMember1 = enquiryMemberRepo.save(enquiryMember);
            if (enquiryMember1 != null) {
                resp.setError("false");
                resp.setMsg("member updated successfully..");
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("member not found..");
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<EnquiryResp> getEnquiryMembers(String ownerId) {
        EnquiryResp resp = new EnquiryResp();
        try {
            List<EnquiryMember> enquiryMembers = enquiryMemberRepo.findByGymOwnerOwnerId(Long.parseLong(ownerId));
            List<GymEnquiryMember> members = new ArrayList<>();
            if (!CollectionUtils.isEmpty(enquiryMembers)) {
                for (EnquiryMember e : enquiryMembers) {
                    GymEnquiryMember member = new GymEnquiryMember();
                    member.setEnquiry_date(e.getEnquiryDate());
                    member.setMember_email(e.getMemberEmail());
                    member.setMember_name(e.getMemberName());
                    member.setMember_mobile(e.getMemberMobile());
                    member.setMember_gender(e.getMemberMobile());
                    member.setEnquiry_desc(e.getEnquiryDesc());
                    members.add(member);
                }
                resp.setError("false");
                resp.setMsg("Enquiry member found..");
                resp.setOwnerId(ownerId);
                resp.setEnquiryMembers(members);
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("Enquiry member not found..");
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
