package com.example.fitness_tracker.entity.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToUser(UserDto userDto);

    User createDtoToUser(CreateUserDto userDto);

    void updateUserFromDto(UserDto userDto, @MappingTarget User user);
}
