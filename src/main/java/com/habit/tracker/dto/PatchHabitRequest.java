package com.habit.tracker.dto;

import com.habit.tracker.model.Frequency;

/*
  PatchHabitRequest
  - Used for PATCH /api/habits/{id}
  - All fields are OPTIONAL. Only fields provided by the client will be applied.
  - No validation annotations here because fields are optional; service will validate semantics if needed.
*/
public class PatchHabitRequest {

    // Optional: update title if present (non-empty validation can be done in service if you want)
    private String title;

    // Optional: update description if present
    private String description;

    // Optional: update frequency if present (use enum values: DAILY or WEEKLY)
    private Frequency frequency;

    // Optional: update startDate if present (ISO string "YYYY-MM-DD")
    private String startDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
