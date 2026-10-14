package com.expense.tracker.dto.Expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewExpenseResponseDto {
    private String title;

    private Double amount;

    private String category;

    private LocalDate date;

}

