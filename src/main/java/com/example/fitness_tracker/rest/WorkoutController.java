package com.example.fitness_tracker.rest;

import com.example.fitness_tracker.entity.workout.Workout;
import com.example.fitness_tracker.entity.workout.WorkoutDto;
import com.example.fitness_tracker.service.WorkoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping()
    List<Workout> getWorkout() {
        return workoutService.findAll();
    }

    @PutMapping("/{id}")
    Workout updateWorkout(@PathVariable("id") int id, @RequestBody WorkoutDto newWorkout) {
        Workout workout = workoutService.findById(id);
        workoutService.updateWorkoutFromDto(newWorkout, workout);

        return workoutService.save(workout);
    }

    @DeleteMapping("/{id}")
    void deleteWorkout(@PathVariable("id") int id) {
        workoutService.deleteWorkout(id);
    }
}
