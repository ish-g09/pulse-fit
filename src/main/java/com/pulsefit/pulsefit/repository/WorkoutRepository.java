package com.pulsefit.pulsefit.repository;

import com.pulsefit.pulsefit.model.WorkoutTelemetryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutTelemetryLog, Long> {

    List<WorkoutTelemetryLog> findByUserEmail(String userEmail);

    // Fetch high-intensity workouts exceeding a heart rate threshold
    @Query("SELECT w FROM WorkoutTelemetryLog w WHERE w.userEmail = :userEmail AND w.heartRate > :threshold")
    List<WorkoutTelemetryLog> findHighIntensityWorkouts(@Param("userEmail") String userEmail, @Param("threshold") Integer threshold);

    // Calculate total calories burned by a user
    @Query("SELECT SUM(w.caloriesBurned) FROM WorkoutTelemetryLog w WHERE w.userEmail = :userEmail")
    Double calculateTotalCaloriesByUser(@Param("userEmail") String userEmail);
}