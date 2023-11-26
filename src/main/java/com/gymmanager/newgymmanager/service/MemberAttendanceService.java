package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.MemberAttendance;
import com.gymmanager.newgymmanager.repository.MemberAttendanceRepo;
import com.gymmanager.newgymmanager.response.GymMemberAttendance;
import com.gymmanager.newgymmanager.response.MemberAttendanceResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberAttendanceService implements MemberAttendanceInterface {
    @Autowired
    private MemberAttendanceRepo memberAttendanceRepo;

    @Override
    public ResponseEntity<MemberAttendanceResp> getAttendanceList(String ownerId) {
        MemberAttendanceResp resp = new MemberAttendanceResp();
        try {
            List<MemberAttendance> members = memberAttendanceRepo.findByGymOwnerOwnerId(Long.parseLong(ownerId));
            List<GymMemberAttendance> gymMembers = new ArrayList<>();
            if (!CollectionUtils.isEmpty(members)) {
                for (MemberAttendance e : members) {
                    GymMemberAttendance member = new GymMemberAttendance();
                    member.setMemberName(e.getMember().getMemberName());
                    member.setPunchDate(e.getPunchDate());
                    member.setPunchInTime(e.getPunchInTime());
                    member.setPunchOutTime(e.getPunchOutTime());
                    gymMembers.add(member);
                }
                resp.setError("false");
                resp.setMsg("members attendance found..");
                resp.setOwnerId(ownerId);
                resp.setMemberAttendances(gymMembers);
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("members attendance not found..");
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

