package com.expense.tracker.controller;

import com.expense.tracker.dto.CreateUserResponseDto;
import com.expense.tracker.dto.Expense.CreateNewExpenseRequestDto;
import com.expense.tracker.dto.Expense.CreateNewExpenseResponseDto;
import com.expense.tracker.model.Expense;
import com.expense.tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping("/new")
    public ResponseEntity<CreateNewExpenseResponseDto> createNewExpense(@Valid @RequestBody CreateNewExpenseRequestDto createNewExpense){
        CreateNewExpenseResponseDto responseDto = expenseService.CreateNewExpense(createNewExpense);
        return ResponseEntity.ok(responseDto);
    }
    @PutMapping("/getAll/{id}")
    public ResponseEntity<List<CreateNewExpenseResponseDto>> getAllExpense( @PathVariable Long id){
       List<CreateNewExpenseResponseDto> rs =  expenseService.GetAllExpenses(id);
       return ResponseEntity.ok(rs);
    }
    @DeleteMapping("/deleteResponse/{id}")
    public ResponseEntity<String> deleteExpense( @PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("deleted successfully");
    }
}
