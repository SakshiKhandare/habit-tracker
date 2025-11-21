package com.habit.tracker.service.impl;

import com.habit.tracker.dto.UpdateHabitRequest;
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
import java.time.LocalDate;
import java.util.List;

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

    @Override
    public List<HabitResponse> getAllHabits() {
        // Fetch all Habit entities from the database
        List<Habit> habits = habitRepository.findAll();

        return habits.stream()
                .map(HabitMapper::toResponse)
                .toList();
    }

    @Override
    public HabitResponse updateHabit(Long id, UpdateHabitRequest request) {

        Habit existingHabit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found with id: "+id));

        existingHabit.setTitle(request.getTitle());
        existingHabit.setDescription(request.getDescription());
        existingHabit.setFrequency(request.getFrequency());

        if(request.getStartDate() != null && !request.getStartDate().isBlank()){
            existingHabit.setStartDate(LocalDate.parse(request.getStartDate()));
        } else {
            existingHabit.setStartDate(null);
        }

        existingHabit.setUpdatedAt(Instant.now());

        Habit saved = habitRepository.save(existingHabit);

        return HabitMapper.toResponse(saved);
    }

    @Override
    public void deleteHabit(Long id) {

        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found with id: "+id));

        habitRepository.delete(habit);
    }

}
