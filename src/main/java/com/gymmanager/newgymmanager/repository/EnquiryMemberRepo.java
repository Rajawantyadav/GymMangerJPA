package com.gymmanager.newgymmanager.repository;

import com.gymmanager.newgymmanager.model.EnquiryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnquiryMemberRepo extends JpaRepository<EnquiryMember, Long> {

    List<EnquiryMember> findByGymOwnerOwnerId(Long ownerId);
}
