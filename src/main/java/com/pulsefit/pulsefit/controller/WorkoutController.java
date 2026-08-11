package com.pulsefit.pulsefit.controller;

import com.pulsefit.pulsefit.model.WorkoutTelemetryLog;
import com.pulsefit.pulsefit.service.AIRecoveryService;
import com.pulsefit.pulsefit.service.WorkoutAnalyticsService;
import com.pulsefit.pulsefit.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/telemetry")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutAnalyticsService analyticsService;
    private final AIRecoveryService aiRecoveryService;

    @PostMapping
    public ResponseEntity<WorkoutTelemetryLog> logWorkout(@RequestBody WorkoutTelemetryLog log) {
        WorkoutTelemetryLog savedLog = workoutService.saveTelemetry(log);
        analyticsService.processWorkoutAsync(savedLog);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutTelemetryLog>> getAllLogs() {
        return ResponseEntity.ok(workoutService.getAllLogs());
    }

    @GetMapping("/user/{userEmail}")
    public ResponseEntity<List<WorkoutTelemetryLog>> getLogsByUser(@PathVariable String userEmail) {
        return ResponseEntity.ok(workoutService.getLogsByUser(userEmail));
    }

    @GetMapping("/ai/recovery/{userEmail}")
    public ResponseEntity<String> getAIRecoveryPlan(@PathVariable String userEmail) {
        String plan = aiRecoveryService.getRecoveryAdviceForUser(userEmail);
        return ResponseEntity.ok(plan);
    }
}