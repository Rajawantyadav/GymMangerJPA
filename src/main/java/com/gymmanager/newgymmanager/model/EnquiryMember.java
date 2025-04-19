package com.gymmanager.newgymmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class EnquiryMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;
    private String memberName;
    private String memberEmail;
    private String memberMobile;
    private String memberGender;
    private String enquiryDate;
    private String enquiryDesc;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private GymOwner gymOwner;
}
