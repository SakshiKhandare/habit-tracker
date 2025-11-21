package com.habit.tracker.dto;

import com.habit.tracker.model.Frequency;

import java.time.Instant;
import java.util.UUID;

/*
  HabitResponse:
  - This DTO is returned to the client after creating a habit.
  - It contains only what should be exposed externally (no internal fields).
  - startDate is kept as a String for JSON compatibility.
*/
public class HabitResponse {

    private Long id;
    private String title;
    private String description;
    private Frequency frequency;
    private String startDate;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
