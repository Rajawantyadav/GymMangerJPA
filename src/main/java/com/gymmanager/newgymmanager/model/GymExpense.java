package com.gymmanager.newgymmanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class GymExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expense_id;
    private String expense_title;
    private String expense_amount;
    private String expense_date;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private GymOwner gymOwner;


}
