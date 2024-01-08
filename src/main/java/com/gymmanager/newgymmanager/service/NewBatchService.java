package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.GymOwner;
import com.gymmanager.newgymmanager.model.NewBatch;
import com.gymmanager.newgymmanager.repository.GymOwnerRepo;
import com.gymmanager.newgymmanager.repository.NewBatchRepo;
import com.gymmanager.newgymmanager.request.BatchReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.BatchResp;
import com.gymmanager.newgymmanager.response.GymBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewBatchService implements NewBatchInterface {
    @Autowired
    private NewBatchRepo newBatchRepo;
    @Autowired
    private GymOwnerRepo gymOwnerRepo;

    @Override
    public ResponseEntity<APiResp> addBatch(BatchReq batchReq) {
        APiResp resp = new APiResp();
        if (batchReq != null) {
            GymOwner gymOwner = gymOwnerRepo.findById(batchReq.getOwnerId()).orElseThrow();
            NewBatch batch = new NewBatch();
            batch.setBatchActive("1");
            batch.setBatchLimit(batchReq.getBatchLimit());
            batch.setBatchName(batchReq.getBatchName());
            batch.setBatchStartTime(batchReq.getBatchStartTime());
            batch.setBatchEndTime(batchReq.getBatchEndTime());
            batch.setGymOwner(gymOwner);
            NewBatch batch1 = newBatchRepo.save(batch);
            if (batch1 != null) {
                resp.setError("false");
                resp.setMsg("New Batch added successfully ..");
                resp.setOwnerId(batch1.getGymOwner().getOwnerId() + "");
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
    public ResponseEntity<BatchResp> getBatch(String ownerId) {
        BatchResp resp = new BatchResp();
        try {
            List<NewBatch> gymBatches = newBatchRepo.findByGymOwnerOwnerId(Long.parseLong(ownerId));
            List<GymBatch> batches = new ArrayList<>();
            if (!CollectionUtils.isEmpty(gymBatches)) {
                for (NewBatch e : gymBatches) {
                    GymBatch batch = new GymBatch();
                    batch.setBatchId(e.getBatchId() + "");
                    batch.setBatchName(e.getBatchName());
                    batch.setBatchLimit(e.getBatchLimit());
                    batch.setBatchEndTime(e.getBatchEndTime());
                    batch.setBatchStartTime(e.getBatchStartTime());
                    batches.add(batch);
                }
                resp.setError("false");
                resp.setMsg("batches found..");
                resp.setOwnerId(ownerId);
                resp.setBatches(batches);
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("batches not found..");
                resp.setBatches(new ArrayList<>());
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
    public ResponseEntity<APiResp> updateBatch(NewBatch batchDetails) {
        APiResp resp = new APiResp();
        if (batchDetails != null) {
            NewBatch batch = newBatchRepo.findById(batchDetails.getBatchId()).orElseThrow();
            batch.setBatchName(batchDetails.getBatchName());
            batch.setBatchLimit(batchDetails.getBatchLimit());
            batch.setBatchEndTime(batchDetails.getBatchEndTime());
            batch.setBatchStartTime(batchDetails.getBatchStartTime());
            NewBatch batch1 = newBatchRepo.save(batch);
            if (batch1 != null) {
                resp.setError("false");
                resp.setMsg("batch updated successfully..");
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("batch not found..");
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
