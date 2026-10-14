package com.expense.tracker.service;

import com.expense.tracker.Exception.ResourceNotFoundException;
import com.expense.tracker.dto.CreateUserResponseDto;
import com.expense.tracker.dto.Expense.CreateNewExpenseRequestDto;
import com.expense.tracker.dto.Expense.CreateNewExpenseResponseDto;
import com.expense.tracker.model.Expense;
import com.expense.tracker.model.user;
import com.expense.tracker.repo.ExpenseRepo;
import com.expense.tracker.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ExpenseService {
    UserRepo userRepo;
    ExpenseRepo expenseRepo;
    public ExpenseService(ExpenseRepo expenseRepo ,  UserRepo userRepo) {
        this.expenseRepo = expenseRepo;
        this.userRepo = userRepo;
    }

    public CreateNewExpenseResponseDto CreateNewExpense(
            CreateNewExpenseRequestDto createNewExpense) {

        user user = userRepo.findById(createNewExpense.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + createNewExpense.getUserId()
                ));

        Expense expense = mapToExpense(createNewExpense);
        expense.setUser(user);

        Expense savedExpense = expenseRepo.save(expense);

        return mapToDto(savedExpense);
    }



    private CreateNewExpenseResponseDto mapToDto(Expense expense) {
        CreateNewExpenseResponseDto expenseResponseDto = new CreateNewExpenseResponseDto();
        expenseResponseDto.setTitle(expense.getTitle());
        expenseResponseDto.setAmount(expense.getAmount());
        expenseResponseDto.setCategory(expense.getCategory());
        expenseResponseDto.setDate(expense.getDate());
        return expenseResponseDto;
    }

    private Expense mapToExpense(CreateNewExpenseRequestDto createNewExpense) {
        Expense expense = new Expense();
        expense.setTitle(createNewExpense.getTitle());
        expense.setAmount(createNewExpense.getAmount());
        expense.setCategory(createNewExpense.getCategory());
        return expense;
    }

    public List<CreateNewExpenseResponseDto>GetAllExpenses(Long id) {
        List<Expense> ls = expenseRepo.findByuser_Id(id);
        return ls.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteExpense(Long id) {
        Expense ex =  expenseRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
        expenseRepo.delete(ex);
    }
}
