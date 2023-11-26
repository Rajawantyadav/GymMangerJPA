package com.gymmanager.newgymmanager.repository;

import com.gymmanager.newgymmanager.model.GymOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymOwnerRepo extends JpaRepository<GymOwner, Long> {
    Optional<GymOwner> findByOwnerEmailAndOwnerPasswordAndOwnerActive(String ownerEmail, String ownerPassword, String ownerActive);
    Optional<GymOwner> findByOwnerId(long ownerId);

}

