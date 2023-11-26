package com.gymmanager.newgymmanager.repository;

import com.gymmanager.newgymmanager.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Long> {
    List<Plan> findByGymOwnerOwnerId(Long ownerId);

    List<Plan> findByPlanName(String memberBatch);
}
