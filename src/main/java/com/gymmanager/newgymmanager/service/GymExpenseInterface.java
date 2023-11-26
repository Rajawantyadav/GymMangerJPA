package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.GymExpense;
import com.gymmanager.newgymmanager.request.ExpenseReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.GymExpenseResp;
import org.springframework.http.ResponseEntity;

public interface GymExpenseInterface {
    ResponseEntity<APiResp> addExpense(ExpenseReq expenseDetails);

    ResponseEntity<APiResp> updateExpense(GymExpense expenseDetails);

    ResponseEntity<GymExpenseResp> getGymExpense(String ownerId);
}
