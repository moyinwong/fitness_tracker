package com.example.fitness_planner.entity.exercise_set;

import com.example.fitness_planner.entity.exercise.Exercise;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise_set")
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(optional = false)
    @JsonBackReference
    Exercise exercise;

    @Column(name = "sets", nullable = false)
    int sets;

    @Column(name = "reps", nullable = false)
    int reps;

    @Column(name = "weight", nullable = false)
    float weight;

    public ExerciseSet() {
    }

    public ExerciseSet(Exercise exercise, int sets, int reps, float weight) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

