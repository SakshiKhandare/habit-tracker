package com.habit.tracker.repository;

import com.habit.tracker.model.Frequency;
import com.habit.tracker.model.Habit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    // Find habits by frequency (e.g., DAILY or WEEKLY)
    List<Habit> findByFrequency(Frequency frequency);

    Page<Habit> findByFrequency(Frequency frequency, Pageable pageable);
}

/*
  HabitRepository:
  - Extends JpaRepository to get built-in CRUD operations.
  - Spring will automatically generate methods like save(), findById(), findAll(), deleteById().
  - No need to write SQL — JPA handles it based on the Habit entity.
*/

/*
  <Habit, Long> here means:
  - Habit → the entity this repository manages.
  - Long  → the type of the primary key (id) of the Habit entity.
  JPA uses these types to know what table to work with and what the ID type is.
*/

/*
  JpaRepository<Habit, Long>

  NOTE:
  - The two generic types <Entity, IdType> are MANDATORY.
  - Entity  → the JPA @Entity class this repository manages (Habit).
  - IdType  → the Java type of the primary key (@Id field) in that entity (Long).

  Why this is required:
  - Spring Data uses the Entity type to know which table to work with.
  - It uses IdType to know the datatype of the primary key when generating:
      save(), findById(), findAll(), deleteById(), etc.

  Without specifying both types, Spring cannot auto-generate CRUD operations.
*/

