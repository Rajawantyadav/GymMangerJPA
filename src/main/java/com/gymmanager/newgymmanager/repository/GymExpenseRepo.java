package com.gymmanager.newgymmanager.repository;

import com.gymmanager.newgymmanager.model.GymExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GymExpenseRepo extends JpaRepository<GymExpense, Long> {
    List<GymExpense> findByGymOwnerOwnerId(Long ownerId);
}
