package com.example.fitness_planner.entity.workout;

import com.example.fitness_planner.entity.exercise.Exercise;

import java.util.List;

public class WorkoutDto {
    int id;
    String name;
    Float duration;
    List<Exercise> exercises;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "WorkoutDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", exercises=" + exercises +
                '}';
    }
}
