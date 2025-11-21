package com.habit.tracker.controllers;

import com.habit.tracker.dto.CreateHabitRequest;
import com.habit.tracker.dto.HabitResponse;
import com.habit.tracker.dto.PatchHabitRequest;
import com.habit.tracker.dto.UpdateHabitRequest;
import com.habit.tracker.model.Frequency;
import com.habit.tracker.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    Supports:
    - filtering by frequency (optional)
    - pagination (optional)
      page -> default 0
      size -> default 10
*/
    @GetMapping
    public ResponseEntity<Page<HabitResponse>> getAllHabits(
            @RequestParam(value = "frequency", required = false) Frequency frequency,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<HabitResponse> habits = habitService.getHabits(frequency, page, size);
        return ResponseEntity.ok(habits);
    }

/*
   PUT /api/habits/{id}
   - Full update of an existing habit.
   - All fields must be provided in request body.
   - If habit doesn't exist -> 404 (handled by GlobalExceptionHandler).
   - If request invalid -> 400 (validation handler covers this).
*/

    @PutMapping("/{id}")
    public ResponseEntity<HabitResponse> updateHabit(
            @PathVariable Long id,
            @Valid @RequestBody UpdateHabitRequest request
            ){
        HabitResponse updated = habitService.updateHabit(id,request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id){
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HabitResponse> patchHabit(
            @PathVariable Long id,
            @RequestBody PatchHabitRequest request
            ){
        HabitResponse updated = habitService.patchHabit(id, request);
        return ResponseEntity.ok(updated);
    }

/*
   POST /api/habits/{id}/complete
   POST /api/habits/{id}/incomplete
   - Marks habit completed or not completed.
   - Returns 200 OK with the updated HabitResponse.
*/

    @PostMapping("/{id}/complete")
    public ResponseEntity<HabitResponse> completeHabit(@PathVariable Long id){
        HabitResponse updated = habitService.completeHabit(id);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/incomplete")
    public ResponseEntity<HabitResponse> markHabitIncomplete(@PathVariable Long id){
        HabitResponse updated = habitService.markHabitIncomplete(id);
        return ResponseEntity.ok(updated);
    }
}
