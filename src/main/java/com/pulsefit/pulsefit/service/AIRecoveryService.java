package com.pulsefit.pulsefit.service;

import com.pulsefit.pulsefit.ai.RecoveryAdvisor;
import com.pulsefit.pulsefit.model.WorkoutTelemetryLog;
import com.pulsefit.pulsefit.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIRecoveryService {

    private final WorkoutRepository workoutRepository;
    private final RecoveryAdvisor recoveryAdvisor;

    public String getRecoveryAdviceForUser(String userEmail) {
        List<WorkoutTelemetryLog> userLogs = workoutRepository.findByUserEmail(userEmail);

        if (userLogs.isEmpty()) {
            return "No workout telemetry found for user " + userEmail + ". Log a workout first to receive AI advice!";
        }

        // Fetch latest workout session for context
        WorkoutTelemetryLog latest = userLogs.get(userLogs.size() - 1);

        String workoutContext = String.format(
                "User: %s | Activity: %s | Heart Rate: %d bpm | Calories: %.1f | Duration: %d seconds",
                latest.getUserEmail(),
                latest.getWorkoutType(),
                latest.getHeartRate(),
                latest.getCaloriesBurned(),
                latest.getDurationSeconds()
        );

        try {
            return recoveryAdvisor.generateRecoveryPlan(workoutContext);
        } catch (Exception e) {
            log.warn("Ollama LLM service unreachable on cloud deployment: {}", e.getMessage());
            return String.format(
                    "Workout data retrieved for %s (%s)! AI Coaching is currently offline on cloud environment. Connect local LLM or Groq API for live advice.",
                    latest.getWorkoutType(),
                    userEmail
            );
        }
    }
}