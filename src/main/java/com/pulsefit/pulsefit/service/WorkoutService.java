package com.pulsefit.pulsefit.service;

import com.pulsefit.pulsefit.model.WorkoutTelemetryLog;
import com.pulsefit.pulsefit.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutTelemetryLog saveTelemetry(WorkoutTelemetryLog log) {
        return workoutRepository.save(log);
    }

    public List<WorkoutTelemetryLog> getAllLogs() {
        return workoutRepository.findAll();
    }

    public List<WorkoutTelemetryLog> getLogsByUser(String userEmail) {
        return workoutRepository.findByUserEmail(userEmail);
    }
}