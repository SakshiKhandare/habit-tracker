package com.habit.tracker.service;

import com.habit.tracker.dto.CreateHabitRequest;
import com.habit.tracker.dto.HabitResponse;

/*
  HabitService
  - Business API for Habit operations.
  - Keep this interface small and focused so implementations are easy to test.
*/
public interface HabitService {

    /**
     * Create a new habit from the given request.
     * - If request.startDate is null, the habit.startDate will be null (meaning starts immediately).
     * - Returns the created HabitResponse containing id, createdAt, etc.
     */
    HabitResponse createHabit(CreateHabitRequest request);

    HabitResponse getHabitById(Long id);
}

