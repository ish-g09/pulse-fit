package com.pulsefit.pulsefit.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "workout_telemetry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutTelemetryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String workoutType; // e.g., "HIIT", "Running", "Strength"

    private Integer heartRate;
    private Double caloriesBurned;
    private Long durationSeconds;

    private LocalDateTime timestamp;

    @PrePersist
    public void onCreate() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
}