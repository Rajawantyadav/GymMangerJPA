package com.gymmanager.newgymmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class GymOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownerId;
    @JsonProperty("owner_name")
    private String ownerName;
    @JsonProperty("owner_email")
    private String ownerEmail;
    @JsonProperty("owner_mobile")
    private String ownerMobile;
    @JsonProperty("owner_password")
    private String ownerPassword;
    private String ownerActive;
    @OneToMany(mappedBy = "gymOwner",cascade = CascadeType.ALL)
    private List<GymExpense> gymExpenses;
    @OneToMany(mappedBy = "gymOwner",cascade = CascadeType.ALL)
    private List<EnquiryMember> enquiryMembers;
    @OneToMany(mappedBy = "gymOwner",cascade = CascadeType.ALL)
    private List<Member> members;
    @OneToMany(mappedBy = "gymOwner",cascade = CascadeType.ALL)
    private List<MemberAttendance> memberAttendances;
    @OneToMany(mappedBy = "gymOwner",cascade = CascadeType.ALL)
    private List<NewBatch> newBatches;
    @OneToMany(mappedBy = "gymOwner",cascade = CascadeType.ALL)
    private List<Plan> plans;
}
