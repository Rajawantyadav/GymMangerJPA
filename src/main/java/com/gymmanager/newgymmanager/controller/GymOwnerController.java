package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.model.GymOwner;
import com.gymmanager.newgymmanager.model.NewBatch;
import com.gymmanager.newgymmanager.request.LoginReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.service.GymOwnerService;
import com.gymmanager.newgymmanager.service.NewBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GymOwnerController {
    @Autowired
    private GymOwnerService gymOwnerService;

    @PostMapping("addGymOwner")
    public ResponseEntity<APiResp> addBatch(@RequestBody GymOwner gymOwner) {
        return gymOwnerService.addGymOwner(gymOwner);
    }
    @PostMapping("login")
    public ResponseEntity<APiResp> login(@RequestBody LoginReq req) {
        return gymOwnerService.login(req);
    }
    @GetMapping("getGymOwner/{ownerId}")
    public ResponseEntity<GymOwner> getGymOwner(@PathVariable String ownerId) {
        return gymOwnerService.getGymOwner(ownerId);
    }
    @PostMapping("updateGymOwner")
    public ResponseEntity<APiResp> updateExpense(@RequestBody GymOwner gymOwnerDetails) {
        return gymOwnerService.updateGymOwner(gymOwnerDetails);
    }
}
