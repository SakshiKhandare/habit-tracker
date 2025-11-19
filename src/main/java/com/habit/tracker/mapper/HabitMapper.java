package com.habit.tracker.mapper;

import com.habit.tracker.dto.CreateHabitRequest;
import com.habit.tracker.dto.HabitResponse;
import com.habit.tracker.model.Frequency;
import com.habit.tracker.model.Habit;

import java.time.LocalDate;

/*
  HabitMapper
  - Converts CreateHabitRequest (DTO, startDate as String) -> Habit (entity, startDate as LocalDate)
  - Converts Habit (entity) -> HabitResponse (DTO, startDate as String)
  - Keeps parsing/formatting logic centralized so controllers/services stay clean.
*/
public class HabitMapper {

    // DTO -> Entity
    public static Habit toEntity(CreateHabitRequest req){

        if(req == null) return null;

        Habit habit = new Habit();
        habit.setTitle(req.getTitle());
        habit.setDescription(req.getDescription());
        habit.setFrequency(req.getFrequency());

        /*
            If user sends startDate → use it.
            If user does NOT send startDate → keep it null.
         */
        if(req.getStartDate() != null && !req.getStartDate().isBlank()){
            habit.setStartDate(LocalDate.parse(req.getStartDate()));
        } else {
            habit.setStartDate(null);
        }

        return habit;
    }

    // Entity -> Response DTO
    public static HabitResponse toResponse(Habit entity){
        if(entity == null)  return null;

        HabitResponse resp = new HabitResponse();
        resp.setId(entity.getId());
        resp.setTitle(entity.getTitle());
        resp.setDescription(entity.getDescription());
        resp.setFrequency(entity.getFrequency());

        // LocalDate -> String
        LocalDate sd = entity.getStartDate();
        resp.setStartDate(sd != null ? sd.toString() : null);

        resp.setCreatedAt(entity.getCreatedAt());
        resp.setUpdatedAt(entity.getUpdatedAt());

        return resp;
    }
}
