package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.NewBatch;
import com.gymmanager.newgymmanager.request.BatchReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.BatchResp;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NewBatchInterface {
    ResponseEntity<APiResp> addBatch(BatchReq batchDetails);
    ResponseEntity<BatchResp> getBatch(String ownerId);
    ResponseEntity<APiResp> updateBatch(NewBatch batchDetails);
}
