package com.gymmanager.newgymmanager.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;
    private String active;
    private String memberName;
    private String joiningDate;
    private String planExpireDate;
    private String memberEmail;
    private String memberMobile;
    private String memberDob;
    private String memberGender;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAttendance> memberAttendances;
    @OneToOne
    private NewBatch memberBatch;
    @OneToOne
    private Plan memberPlan;
    private String memberWeight;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private GymOwner gymOwner;


}
