package com.pulsefit.pulsefit.controller;

import com.pulsefit.pulsefit.model.WorkoutTelemetryLog;
import com.pulsefit.pulsefit.service.AIRecoveryService;
import com.pulsefit.pulsefit.service.WorkoutAnalyticsService;
import com.pulsefit.pulsefit.service.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/telemetry")
@RequiredArgsConstructor
@Tag(name = "Telemetry & AI Recovery Engine", description = "Endpoints for ingesting athletic workout telemetry and generating LLM recovery plans.")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutAnalyticsService analyticsService;
    private final AIRecoveryService aiRecoveryService;

    @PostMapping
    @Operation(
            summary = "Ingest Workout Telemetry Payload",
            description = "Saves raw workout telemetry into PostgreSQL and asynchronously triggers Virtual Thread analytics processing."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Telemetry successfully saved and queued for analytics processing"),
            @ApiResponse(responseCode = "400", description = "Invalid telemetry payload supplied")
    })
    public ResponseEntity<WorkoutTelemetryLog> logWorkout(@RequestBody WorkoutTelemetryLog log) {
        WorkoutTelemetryLog savedLog = workoutService.saveTelemetry(log);
        analyticsService.processWorkoutAsync(savedLog);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Telemetry Logs", description = "Retrieves all workout logs stored in the system.")
    public ResponseEntity<List<WorkoutTelemetryLog>> getAllLogs() {
        return ResponseEntity.ok(workoutService.getAllLogs());
    }

    @GetMapping("/user/{userEmail}")
    @Operation(summary = "Get Workout Telemetry by User Email", description = "Fetches complete workout history for a specific athlete.")
    public ResponseEntity<List<WorkoutTelemetryLog>> getLogsByUser(
            @Parameter(description = "Athlete's email address", example = "testuser@pulsefit.com")
            @PathVariable String userEmail) {
        return ResponseEntity.ok(workoutService.getLogsByUser(userEmail));
    }

    @GetMapping("/ai/recovery/{userEmail}")
    @Operation(summary = "Generate AI Recovery Plan", description = "Analyzes an athlete's latest workout telemetry using Groq Cloud LLM (Llama 3.3) and generates a personalized recovery plan.")
    public ResponseEntity<String> getAIRecoveryPlan(
            @Parameter(description = "Athlete's email address", example = "testuser@pulsefit.com")
            @PathVariable String userEmail) {
        String plan = aiRecoveryService.getRecoveryAdviceForUser(userEmail);
        return ResponseEntity.ok(plan);
    }
}