package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.GymOwner;
import com.gymmanager.newgymmanager.request.LoginReq;
import com.gymmanager.newgymmanager.response.APiResp;
import org.springframework.http.ResponseEntity;

public interface GymOwnerInterface {
    ResponseEntity< APiResp> updateGymOwner(GymOwner gymOwnerDetails);
    ResponseEntity<APiResp> addGymOwner(GymOwner gymOwnerDetails);
    ResponseEntity<GymOwner> getGymOwner(String ownerId);

    ResponseEntity<APiResp> login(LoginReq req);
}
