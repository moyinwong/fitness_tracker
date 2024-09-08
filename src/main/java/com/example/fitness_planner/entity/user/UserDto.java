package com.example.fitness_planner.entity.user;

import com.example.fitness_planner.entity.workout.Workout;

import java.util.List;

public class UserDto {
    public String firstName;
    public String lastName;
    public String username;
    public List<Workout> workouts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}

