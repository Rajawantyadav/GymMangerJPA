package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.model.NewBatch;
import com.gymmanager.newgymmanager.request.BatchReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.BatchResp;
import com.gymmanager.newgymmanager.service.NewBatchService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BatchController {
    @Autowired
    private NewBatchService batchService;

    @PostMapping("addBatch")
    public ResponseEntity<APiResp> addBatch(HttpServletRequest  request,@RequestBody BatchReq batchReq) {
        String token =request.getHeader("Authorization");
        if(token==null || token.isEmpty()) {
            APiResp aPiResp=new APiResp();
            aPiResp.setError("true");
            return new ResponseEntity<>(aPiResp, HttpStatus.FORBIDDEN);
        }
        return batchService.addBatch(batchReq);
    }
    @GetMapping("/getBatch")
    public ResponseEntity<BatchResp> getBatch(HttpServletRequest request) {
        String token =request.getHeader("Authorization");
        if(token!=null && !token.isEmpty()) {
            return batchService.getBatch(token);
        }else{
            BatchResp batchResp=new BatchResp();
            batchResp.setError("true");
            return new ResponseEntity<>(batchResp, HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping("updateBatch")
    public ResponseEntity<APiResp> updateBatch(HttpServletRequest request,@RequestBody NewBatch batchDetails) {
        String token =request.getHeader("Authorization");
        if(token==null || token.isEmpty()) {
            APiResp aPiResp=new APiResp();
            aPiResp.setError("true");
            return new ResponseEntity<>(aPiResp, HttpStatus.FORBIDDEN);
        }
        return batchService.updateBatch(batchDetails);
    }
}
