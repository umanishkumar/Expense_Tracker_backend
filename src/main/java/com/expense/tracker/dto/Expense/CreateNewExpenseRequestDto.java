package com.expense.tracker.dto.Expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewExpenseRequestDto {
    @NotBlank(message = "enter the title")
    private String title;
    @NotNull(message = "enter the amount")
    private Double amount;
    @NotBlank(message = "enter the category which you have sent your amount")
    private String category;

    private LocalDate date;

    private Long userId;
}
