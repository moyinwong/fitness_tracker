package com.example.fitness_planner.entity.exercise;

import com.example.fitness_planner.entity.exercise_set.ExerciseSet;
import com.example.fitness_planner.entity.workout.Workout;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name", nullable = false)
    String name;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonManagedReference
    List<ExerciseSet> exerciseSets;

    @ManyToOne(optional = false)
    @JsonBackReference
    Workout workout;

    public Exercise() {
    }

    public Exercise(String name, List<ExerciseSet> exerciseSets, Workout workout) {
        this.name = name;
        this.exerciseSets = exerciseSets;
        this.workout = workout;
    }

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

    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void addSet(ExerciseSet set) {
        exerciseSets.add(set);
        set.setExercise(this);
    }
}

