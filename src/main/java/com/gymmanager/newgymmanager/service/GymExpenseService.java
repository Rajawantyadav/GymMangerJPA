package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.GymExpense;
import com.gymmanager.newgymmanager.model.GymOwner;
import com.gymmanager.newgymmanager.repository.GymExpenseRepo;
import com.gymmanager.newgymmanager.repository.GymOwnerRepo;
import com.gymmanager.newgymmanager.request.Expense;
import com.gymmanager.newgymmanager.request.ExpenseReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.GymExpenseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymExpenseService implements GymExpenseInterface {
    @Autowired
    private GymExpenseRepo gymExpenseRepo;

    @Autowired
    private GymOwnerRepo gymOwnerRepo;


    @Override
    public ResponseEntity<APiResp> addExpense(ExpenseReq expenseReq) {
        APiResp resp = new APiResp();
        if (expenseReq != null) {
            GymOwner gymOwner = gymOwnerRepo.findById(expenseReq.getOwnerId()).orElseThrow();
            GymExpense expense = new GymExpense();
            expense.setExpense_title(expenseReq.getExpense_title());
            expense.setExpense_amount(expenseReq.getExpense_amount());
            expense.setExpense_date(expenseReq.getExpense_date());
            expense.setGymOwner(gymOwner);
            GymExpense gymExpense = gymExpenseRepo.save(expense);
            if (gymExpense != null) {
                resp.setError("false");
                resp.setMsg("gymExpense added successfully ..");
                resp.setOwnerId(gymExpense.getGymOwner().getOwnerId() + "");
                return new ResponseEntity<>(resp, HttpStatus.CREATED);
            } else {
                resp.setError("true");
                resp.setMsg("Some Exception has occurred.. ");
                return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            resp.setError("true");
            resp.setMsg("Some Parameters missing ??.");
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<APiResp> updateExpense(GymExpense expenseDetails) {
        APiResp resp = new APiResp();
        if (expenseDetails != null) {
            GymExpense gymExpense = gymExpenseRepo.findById(expenseDetails.getExpense_id()).orElseThrow();
            gymExpense.setExpense_amount(expenseDetails.getExpense_amount());
            gymExpense.setExpense_title(expenseDetails.getExpense_title());
            gymExpense.setExpense_date(expenseDetails.getExpense_date());
            GymExpense gymExpense1 = gymExpenseRepo.save(gymExpense);
            if (gymExpense1 != null) {
                resp.setError("false");
                resp.setMsg("gymExpense updated successfully..");
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("gymExpense not found..");
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<GymExpenseResp> getGymExpense(String ownerId) {
        GymExpenseResp resp = new GymExpenseResp();
        try {
            List<GymExpense> gymExpense = gymExpenseRepo.findByGymOwnerOwnerId(Long.parseLong(ownerId));
            List<Expense> expenses = new ArrayList<>();
            if (!CollectionUtils.isEmpty(gymExpense)) {
                for (GymExpense e : gymExpense) {
                    Expense expense = new Expense();
                    expense.setExpense_amount(e.getExpense_amount());
                    expense.setExpense_title(e.getExpense_title());
                    expense.setExpense_date(e.getExpense_date());
                    expense.setExpense_date(e.getExpense_date());
                    expenses.add(expense);
                }
                resp.setError("false");
                resp.setMsg("Expenses found..");
                resp.setOwnerId(ownerId);
                resp.setExpenses(expenses);
                return new ResponseEntity<>(resp, HttpStatus.OK);
            } else {
                resp.setError("true");
                resp.setMsg("Expenses not found..");
                resp.setOwnerId(ownerId);
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            resp.setError("true");
            resp.setMsg("Exception found..");
            resp.setOwnerId(ownerId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
