package com.pulsefit.pulsefit.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "workout_telemetry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Workout telemetry payload collected from wearable or athlete input")
public class WorkoutTelemetryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique database log identifier", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Athlete's account email address", example = "testuser@pulsefit.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userEmail;

    @Column(nullable = false)
    @Schema(description = "Type of activity performed", example = "RUNNING", requiredMode = Schema.RequiredMode.REQUIRED)
    private String workoutType; // e.g., "HIIT", "Running", "Strength"

    @Schema(description = "Average heart rate during workout (BPM)", example = "158")
    private Integer heartRate;

    @Schema(description = "Total estimated calories burned during workout", example = "450.5")
    private Double caloriesBurned;

    @Schema(description = "Duration of workout in seconds", example = "2700")
    private Long durationSeconds;

    @Schema(description = "Timestamp when the workout took place", example = "2026-08-13T14:30:00")
    private LocalDateTime timestamp;

    @PrePersist
    public void onCreate() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
}