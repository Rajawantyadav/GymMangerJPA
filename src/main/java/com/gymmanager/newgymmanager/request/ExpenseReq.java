package com.gymmanager.newgymmanager.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseReq {
    private String expense_title;
    private String expense_amount;
    private String expense_date;
    private long ownerId;
}
