package com.habit.tracker.service.impl;

import com.habit.tracker.exception.HabitNotFoundException;

import com.habit.tracker.dto.CreateHabitRequest;
import com.habit.tracker.dto.HabitResponse;
import com.habit.tracker.mapper.HabitMapper;
import com.habit.tracker.model.Habit;
import com.habit.tracker.repository.HabitRepository;
import com.habit.tracker.service.HabitService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    public HabitServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public HabitResponse createHabit(CreateHabitRequest request) {

        // Map DTO -> Entity
        Habit habit = HabitMapper.toEntity(request);

        Instant now = Instant.now();
        if(habit.getCreatedAt() == null){
            habit.setCreatedAt(now);
        }
        habit.setUpdatedAt(now);

        Habit saved = habitRepository.save(habit);

        // Map Entity -> Response DTO
        return HabitMapper.toResponse(saved);
    }

    @Override
    public HabitResponse getHabitById(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found with id: "+id));

        return HabitMapper.toResponse(habit);
    }
}
