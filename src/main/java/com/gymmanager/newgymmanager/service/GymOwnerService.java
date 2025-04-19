package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.jwtconfig.JwtService;
import com.gymmanager.newgymmanager.model.GymOwner;
import com.gymmanager.newgymmanager.repository.GymOwnerRepo;
import com.gymmanager.newgymmanager.request.LoginReq;
import com.gymmanager.newgymmanager.response.APiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GymOwnerService implements GymOwnerInterface {
    @Autowired
    private GymOwnerRepo gymOwnerRepo;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public ResponseEntity<APiResp> updateGymOwner(GymOwner gymOwnerDetails) {
        APiResp resp = new APiResp();
        if (gymOwnerDetails != null) {
            GymOwner gymOwner = gymOwnerRepo.findById(gymOwnerDetails.getOwnerId()).orElseThrow();
            gymOwner.setOwnerEmail(gymOwnerDetails.getOwnerEmail());
            gymOwner.setOwnerName(gymOwnerDetails.getOwnerName());
            gymOwner.setOwnerMobile(gymOwnerDetails.getOwnerMobile());
            gymOwner.setOwnerPassword(gymOwnerDetails.getOwnerPassword());
            GymOwner gymOwner1 = gymOwnerRepo.save(gymOwner);
            if (gymOwner1 != null) {
                resp.setError("false");
                resp.setMsg("gym owner updated successfully..");
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("gym owner not found..");
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<APiResp> addGymOwner(GymOwner gymOwnerDetails) {
        APiResp resp = new APiResp();
        if (gymOwnerDetails != null) {
            gymOwnerDetails.setOwnerActive("1");
            gymOwnerDetails.setOwnerPassword(bCryptPasswordEncoder.encode(gymOwnerDetails.getOwnerPassword()));
            GymOwner gymOwner = gymOwnerRepo.save(gymOwnerDetails);
            if (gymOwner != null) {
                resp.setError("false");
                resp.setMsg("gymOwnerDetails added successfully ..");
                resp.setOwnerId(gymOwner.getOwnerId() + "");
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
    public ResponseEntity<GymOwner> getGymOwner(String ownerId) {
        GymOwner gymOwner = gymOwnerRepo.findByOwnerId(Long.parseLong(ownerId)).orElseThrow();
        if (gymOwner != null) {
            return new ResponseEntity<>(gymOwner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gymOwner, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<APiResp> login(LoginReq req) {
        String token = "";
        APiResp resp = new APiResp();
        if (req != null) {
            // GymOwner gymOwner = gymOwnerRepo.findByOwnerEmailAndOwnerPasswordAndOwnerActive(req.getUserEmailId(), bCryptPasswordEncoder.encode(req.getPassword()), "1").orElseThrow();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUserEmailId(), req.getPassword()));
            GymOwner gymOwner = gymOwnerRepo.findByOwnerEmail(req.getUserEmailId());
            if (authentication.isAuthenticated()) {
                token = jwtService.generateToken(gymOwner);
                resp.setToken(token);
                resp.setError("false");
                resp.setMsg("Login successfully..");
                resp.setOwnerId(gymOwner.getOwnerId() + "");
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("email or password is incorrect..");
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }
        }
        resp.setError("true");
        resp.setMsg("Some Parameters missing ??.");
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
