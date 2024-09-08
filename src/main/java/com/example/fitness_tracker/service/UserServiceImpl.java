package com.example.fitness_tracker.service;

import com.example.fitness_tracker.entity.user.*;
import com.example.fitness_tracker.entity.workout.WorkoutDto;
import com.example.fitness_tracker.exceptions.UnauthorizedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository1, UserMapper userMapper) {
        this.userRepository = userRepository1;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElseThrow();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createDtoToUser(CreateUserDto userDto) {
        return userMapper.createDtoToUser(userDto);
    }

    @Override
    public User dtoToUser(UserDto userDto) {
        return userMapper.dtoToUser(userDto);
    }

    @Override
    public void updateUserFromDto(UserDto userDto, User user) {
        userMapper.updateUserFromDto(userDto, user);
    }

    @Override
    public User addWorkoutToUser(WorkoutDto workoutDto, int userId) {
        User user = findById(userId);

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findDistinctByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("Incorrect username or password"));
    }

    @Override
    public Boolean isPasswordHashMatch(String password, String hash) {
        return new BCryptPasswordEncoder().matches(password, hash);
    }
}
