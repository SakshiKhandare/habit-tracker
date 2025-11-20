package com.habit.tracker.controllers;

import com.habit.tracker.dto.CreateHabitRequest;
import com.habit.tracker.dto.HabitResponse;
import com.habit.tracker.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
}
