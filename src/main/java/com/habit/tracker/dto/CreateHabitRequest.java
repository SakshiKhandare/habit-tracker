package com.habit.tracker.dto;

import com.habit.tracker.model.Frequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO for POST /api/habits — validated request body for creating a habit
public class CreateHabitRequest {

    @NotBlank(message="Title is required")
    @Size(max = 150, message = "Title cannot exceed 150 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Frequency is required")
    private Frequency frequency;

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

    /*
        Field Type	<-> Use
        String	<-> @NotBlank
        Enum	<-> @NotNull
     */

    /*
        NOTE:
        DTO uses String for startDate because clients send dates as JSON strings (e.g., "2025-11-20").
        The Entity uses LocalDate for strong typing and easier business logic.
        Conversion between String ↔ LocalDate happens inside the Mapper layer.
    */
}
