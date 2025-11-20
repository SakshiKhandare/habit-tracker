package com.habit.tracker.exception;

/*
  HabitNotFoundException
  - Thrown when a habit with the given id is not found in the database.
*/

public class HabitNotFoundException extends RuntimeException{

    public HabitNotFoundException(String message){
        super(message);
    }
}
