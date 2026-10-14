package com.expense.tracker.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class updateUserRequestDto {
    @NotBlank(message ="name should not be null")
    private String username;
    @Email(message = "please enter the valid mail id")
    private String email;
}
