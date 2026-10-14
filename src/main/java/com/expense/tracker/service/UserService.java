package com.expense.tracker.service;

import com.expense.tracker.Exception.ResourceNotFoundException;
import com.expense.tracker.dto.CreateUserRequestDto;
import com.expense.tracker.dto.CreateUserResponseDto;
import com.expense.tracker.dto.UserResponseDto;
import com.expense.tracker.dto.updateUserRequestDto;
import com.expense.tracker.model.user;
import com.expense.tracker.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    UserRepo userRepo;
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    public CreateUserResponseDto createUser(CreateUserRequestDto u) {
        user user = mapToEntity(u);
       user usersave =  userRepo.save(user);
       return mapToDto(usersave);
    }

    public UserResponseDto FindUser(Long id) {
        user foundUser = userRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        UserResponseDto dto = new UserResponseDto();
        dto.setUsername(foundUser.getUsername());
        dto.setEmail(foundUser.getEmail());

        return dto;
    }

    public user mapToEntity(CreateUserRequestDto u) {
        user user = new user();
        user.setUsername(u.getUsername());
        user.setEmail(u.getEmail());
        return user;
    }
    public CreateUserResponseDto mapToDto(user user) {
        CreateUserResponseDto userResponseDto = new CreateUserResponseDto();
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }


    public UserResponseDto UpdateUser(Long id, updateUserRequestDto u) {
        user existingUser = userRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setUsername(u.getUsername());
        existingUser.setEmail(u.getEmail());

        user updatedUser = userRepo.save(existingUser);

        return mapToUserResponseDto(updatedUser);
    }
    public UserResponseDto mapToUserResponseDto(user user) {

        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());

        return responseDto;
    }

    public void deleteUser(Long id) {
        user existingUser = userRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
        userRepo.delete(existingUser);
    }
}
