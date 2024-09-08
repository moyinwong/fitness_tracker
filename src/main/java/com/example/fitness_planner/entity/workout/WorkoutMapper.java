package com.example.fitness_planner.entity.workout;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWorkoutFromDto(WorkoutDto WorkoutDto, @MappingTarget Workout workout);

    Workout dtoToWorkout(WorkoutDto dto);
}
