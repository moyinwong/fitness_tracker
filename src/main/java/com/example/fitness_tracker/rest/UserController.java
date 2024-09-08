package com.example.fitness_tracker.rest;

import com.example.fitness_tracker.auth.JwtService;
import com.example.fitness_tracker.entity.user.CreateUserDto;
import com.example.fitness_tracker.entity.user.LoginDto;
import com.example.fitness_tracker.entity.user.LoginSuccessResponse;
import com.example.fitness_tracker.entity.user.User;
import com.example.fitness_tracker.entity.workout.Workout;
import com.example.fitness_tracker.service.UserService;
import com.example.fitness_tracker.service.WorkoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserController(UserService userService,
                          WorkoutService workoutService, AuthenticationManager authenticationManager,
                          JwtService jwtService
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginSuccessResponse login(@RequestBody LoginDto loginDto) {
        Authentication request = UsernamePasswordAuthenticationToken.unauthenticated(loginDto.username(),
                loginDto.password());

        Authentication result = authenticationManager.authenticate(request);
        log.error("error");
        String accessToken = jwtService.createToken(loginDto.username());
        return new LoginSuccessResponse(accessToken);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @PostMapping()
    public LoginSuccessResponse createUser(@RequestBody CreateUserDto userDto) {
        User user = null;
        try {
            user = userService.findByUsername(userDto.username());
        } catch (Exception _) {
            log.info("catching error");
        }

        if (user != null) {
            throw new AccessDeniedException("Forbidden");
        }

        return new LoginSuccessResponse("something");
    }

    @PostMapping("/{id}/workout")
    public User saveWorkoutForUser(@PathVariable("id") int id, @RequestBody Workout workout) {
        User user = userService.findById(id);
        user.addWorkout(workout);
        return userService.save(user);
    }

}
