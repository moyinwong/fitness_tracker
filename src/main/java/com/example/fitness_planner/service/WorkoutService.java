package com.example.fitness_planner.service;

import com.example.fitness_planner.entity.workout.Workout;
import com.example.fitness_planner.entity.workout.WorkoutDto;

import java.util.List;

public interface WorkoutService {
    Workout findById(int id);

    List<Workout> findAll();

    Workout save(Workout workout);

    void updateWorkoutFromDto(WorkoutDto dto, Workout workout);

    Workout dtoToWorkout(WorkoutDto dto);

    void deleteWorkout(int id);
}
