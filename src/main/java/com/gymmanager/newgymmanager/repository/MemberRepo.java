package com.gymmanager.newgymmanager.repository;

import com.gymmanager.newgymmanager.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    List<Member> findByGymOwnerOwnerId(Long ownerId);
}
