package com.pulsefit.pulsefit.service;

import com.pulsefit.pulsefit.model.WorkoutTelemetryLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorkoutAnalyticsService {

    @Async
    public void processWorkoutAsync(WorkoutTelemetryLog logData) {
        log.info("[Virtual Thread: {}] Starting async strain calculation for user: {}",
                Thread.currentThread(), logData.getUserEmail());

        try {
            // Simulate heavy telemetry crunching
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Safe null handling to avoid NullPointerException
        int heartRate = logData.getHeartRate() != null ? logData.getHeartRate() : 0;
        long duration = logData.getDurationSeconds() != null ? logData.getDurationSeconds() : 0L;

        double intensityIndex = (heartRate * duration) / 1000.0;
        log.info("[Virtual Thread: {}] Completed strain calculation! Strain Index: {}",
                Thread.currentThread(), intensityIndex);
    }
}