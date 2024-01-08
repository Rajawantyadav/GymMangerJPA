package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.model.GymExpense;
import com.gymmanager.newgymmanager.request.ExpenseReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.GymExpenseResp;
import com.gymmanager.newgymmanager.service.GymExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {
    @Autowired
    private GymExpenseService expenseService;

    @PostMapping("addExpense")
    public ResponseEntity<APiResp> addExpense(@RequestBody ExpenseReq expenseReq) {
        return expenseService.addExpense(expenseReq);
    }
    @GetMapping("getGymExpense/{ownerId}")
    public ResponseEntity<GymExpenseResp> getGymExpense(@PathVariable String ownerId) {
        return expenseService.getGymExpense(ownerId);
    }
    @PostMapping("updateExpense")
    public ResponseEntity<APiResp> updateExpense(@RequestBody GymExpense expenseDetails) {
        return expenseService.updateExpense(expenseDetails);
    }
    @GetMapping("testmsg")
    public ResponseEntity<String> updateExpense() {
        return new ResponseEntity<>("Hello docker",HttpStatus.ACCEPTED);
    }

}
