package com.example.fitness_planner.service;

import com.example.fitness_planner.entity.user.CreateUserDto;
import com.example.fitness_planner.entity.user.User;
import com.example.fitness_planner.entity.user.UserDto;
import com.example.fitness_planner.entity.workout.WorkoutDto;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    User save(User user);

    User createDtoToUser(CreateUserDto userDto);

    User dtoToUser(UserDto userDto);

    void updateUserFromDto(UserDto userDto, User user);

    User addWorkoutToUser(WorkoutDto user, int id);

    User findByUsername(String username);

    Boolean isPasswordHashMatch(String password, String hash);
}
