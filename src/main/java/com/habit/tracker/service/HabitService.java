package com.habit.tracker.service;

import com.habit.tracker.dto.CreateHabitRequest;
import com.habit.tracker.dto.HabitResponse;
import com.habit.tracker.dto.PatchHabitRequest;
import com.habit.tracker.dto.UpdateHabitRequest;
import com.habit.tracker.model.Frequency;
import org.springframework.data.domain.Page;

import java.util.List;

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

    List<HabitResponse> getAllHabits();

    HabitResponse updateHabit(Long id, UpdateHabitRequest request);

    void deleteHabit(Long id);

    HabitResponse patchHabit(Long id, PatchHabitRequest request);

    List<HabitResponse> getHabitsByFrequency(Frequency frequency);

    Page<HabitResponse> getHabits(Frequency frequency, int page, int size);

    HabitResponse completeHabit(Long id);

    HabitResponse markHabitIncomplete(Long id);
}

