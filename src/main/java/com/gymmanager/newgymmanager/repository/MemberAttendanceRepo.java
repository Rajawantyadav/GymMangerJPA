package com.gymmanager.newgymmanager.repository;

import com.gymmanager.newgymmanager.model.MemberAttendance;
import com.gymmanager.newgymmanager.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAttendanceRepo extends JpaRepository<MemberAttendance,Long> {
    List<MemberAttendance> findByGymOwnerOwnerId(Long ownerId);
}
