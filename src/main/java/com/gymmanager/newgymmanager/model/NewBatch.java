package com.gymmanager.newgymmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class NewBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long batchId;
    private String batchName;
    private String batchStartTime;
    private String batchEndTime;
    @JsonProperty("limit")
    private String batchLimit;
    private String batchActive;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private GymOwner gymOwner;


}
