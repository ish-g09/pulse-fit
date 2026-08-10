package com.pulsefit.pulsefit.repository;

import com.pulsefit.pulsefit.model.WorkoutTelemetryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutTelemetryLog, Long> {
    List<WorkoutTelemetryLog> findByUserEmail(String userEmail);
}