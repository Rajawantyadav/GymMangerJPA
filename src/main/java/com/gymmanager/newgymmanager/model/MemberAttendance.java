package com.gymmanager.newgymmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberAttendanceId;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    private String punchDate;
    private String punchInTime;
    private String punchOutTime;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private GymOwner gymOwner;

}
