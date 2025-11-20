package com.habit.tracker.controllers;

import com.habit.tracker.dto.CreateHabitRequest;
import com.habit.tracker.dto.HabitResponse;
import com.habit.tracker.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
  HabitController
  - This class handles all HTTP requests coming to /api/habits
  - It talks to HabitService (business logic layer)
  - Converts incoming JSON -> DTO, and returns DTO -> JSON
*/

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    // Spring injects HabitServiceImpl here automatically
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

/*
   POST /api/habits
   - @PostMapping marks this method as an API endpoint for creating a habit
   - @Valid enables validation on CreateHabitRequest
   - @RequestBody tells Spring to convert JSON body into CreateHabitRequest object
*/

    @PostMapping
    public ResponseEntity<HabitResponse> createHabit(@Valid @RequestBody CreateHabitRequest request){
        HabitResponse created = habitService.createHabit(request);
        URI location = URI.create("/api/habits/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

/*
   GET /api/habits/{id}
   - Fetch a single habit by its id.
   - Returns 200 OK with HabitResponse if found.
   - Returns 404 Not Found if id does not exist (handled by GlobalExceptionHandler).
*/

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponse> getHabitById(@PathVariable Long id){
        HabitResponse habit = habitService.getHabitById(id);
        return ResponseEntity.ok(habit);
    }

/*
   GET /api/habits
   - Returns a list of all habits
   - 200 OK with List<HabitResponse>
*/

    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAllHabits(){
        List<HabitResponse> habits = habitService.getAllHabits();
        return ResponseEntity.ok(habits);
    }
}
