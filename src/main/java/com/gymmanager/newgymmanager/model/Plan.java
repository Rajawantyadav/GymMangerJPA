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
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planId;
    private String planName;
    private String planPrice;
    private String planDuration;
    private String planDescription;
    private String planAcive;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private GymOwner gymOwner;
}
