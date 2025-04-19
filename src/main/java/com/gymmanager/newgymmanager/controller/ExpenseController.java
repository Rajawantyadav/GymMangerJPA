package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.model.GymExpense;
import com.gymmanager.newgymmanager.request.ExpenseReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.GymExpenseResp;
import com.gymmanager.newgymmanager.service.GymExpenseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExpenseController {
    @Autowired
    private GymExpenseService expenseService;

    @PostMapping("addExpense")
    public ResponseEntity<APiResp> addExpense(HttpServletRequest request, @RequestBody ExpenseReq expenseReq) {
        String token =request.getHeader("Authorization");
        if(token==null || token.isEmpty()) {
            APiResp aPiResp=new APiResp();
            aPiResp.setError("true");
            return new ResponseEntity<>(aPiResp, HttpStatus.FORBIDDEN);
        }
        return expenseService.addExpense(expenseReq);
    }
    @GetMapping("getGymExpense/{ownerId}")
    public ResponseEntity<GymExpenseResp> getGymExpense(@PathVariable String ownerId) {
        return expenseService.getGymExpense(ownerId);
    }
    @PostMapping("updateExpense")
    public ResponseEntity<APiResp> updateExpense( HttpServletRequest request,@RequestBody GymExpense expenseDetails) {
        String token =request.getHeader("Authorization");
        if(token==null || token.isEmpty()) {
            APiResp aPiResp=new APiResp();
            aPiResp.setError("true");
            return new ResponseEntity<>(aPiResp, HttpStatus.FORBIDDEN);
        }
        return expenseService.updateExpense(expenseDetails);
    }
    @GetMapping("testmsg")
    public ResponseEntity<String> updateExpense() {
        return new ResponseEntity<>("Hello docker",HttpStatus.ACCEPTED);
    }

}
