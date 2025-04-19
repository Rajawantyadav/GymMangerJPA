package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.model.NewBatch;
import com.gymmanager.newgymmanager.request.BatchReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.BatchResp;
import com.gymmanager.newgymmanager.service.NewBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BatchController {
    @Autowired
    private NewBatchService batchService;

    @PostMapping("addBatch")
    public ResponseEntity<APiResp> addBatch(@RequestBody BatchReq batchReq) {
        return batchService.addBatch(batchReq);
    }
    @GetMapping("getBatch/{ownerId}")
    public ResponseEntity<BatchResp> getBatch(@PathVariable String ownerId) {
        return batchService.getBatch(ownerId);
    }
    @PostMapping("updateBatch")
    public ResponseEntity<APiResp> updateBatch(@RequestBody NewBatch batchDetails) {
        return batchService.updateBatch(batchDetails);
    }
}
