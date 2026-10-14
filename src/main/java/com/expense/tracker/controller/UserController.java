package com.expense.tracker.controller;

import com.expense.tracker.dto.CreateUserRequestDto;
import com.expense.tracker.dto.CreateUserResponseDto;
import com.expense.tracker.dto.UserResponseDto;
import com.expense.tracker.dto.updateUserRequestDto;
import com.expense.tracker.model.user;
import com.expense.tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class UserController {
    UserService expense;
    public UserController(UserService expense){
        this.expense = expense;
    }
    @PostMapping("/createUser")
    public ResponseEntity<CreateUserResponseDto> createuser(@Valid @RequestBody CreateUserRequestDto u){
        CreateUserResponseDto responseDto = expense.createUser(u);
        return ResponseEntity.ok().body(responseDto);

    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findUser( @PathVariable Long id){
        UserResponseDto response = expense.FindUser(id);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto>updateuser(@PathVariable Long id ,@Valid @RequestBody updateUserRequestDto u){
        UserResponseDto res = expense.UpdateUser(id , u);
        return  ResponseEntity.ok().body(res);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
         expense.deleteUser(id);
        return  ResponseEntity.ok(" User deleted successfully");
    }
}
