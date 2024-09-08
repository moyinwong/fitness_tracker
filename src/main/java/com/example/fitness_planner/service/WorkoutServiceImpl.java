package com.example.fitness_planner.service;

import com.example.fitness_planner.entity.workout.Workout;
import com.example.fitness_planner.entity.workout.WorkoutDto;
import com.example.fitness_planner.entity.workout.WorkoutMapper;
import com.example.fitness_planner.entity.workout.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
    }

    @Override
    public Workout findById(int id) {
        return workoutRepository.findById(id).orElseThrow(() -> new RuntimeException("Workout not found"));
    }

    @Override
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public void updateWorkoutFromDto(WorkoutDto dto, Workout workout) {
        workoutMapper.updateWorkoutFromDto(dto, workout);
    }

    @Override
    public Workout dtoToWorkout(WorkoutDto dto) {
        return workoutMapper.dtoToWorkout(dto);
    }

    @Override
    public void deleteWorkout(int id) {
        workoutRepository.deleteById(id);
    }
}
