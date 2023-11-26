package com.gymmanager.newgymmanager.repository;

import com.gymmanager.newgymmanager.model.NewBatch;
import com.gymmanager.newgymmanager.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewBatchRepo extends JpaRepository<NewBatch,Long> {
    List<NewBatch> findByGymOwnerOwnerId(Long ownerId);
    List<NewBatch> findByBatchName(String batchName);
}
