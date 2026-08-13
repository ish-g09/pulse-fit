package com.pulsefit.pulsefit.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "chatLanguageModel")
public interface RecoveryAdvisor {

    @SystemMessage("""
        You are PulseFit AI, an elite sports science and athletic recovery specialist.
        Analyze the provided workout telemetry data and give concise, actionable recovery advice.
        Focus on hydration, sleep, active recovery, and injury prevention based on intensity.
        Keep your advice under 120 words.
        """)
    String generateRecoveryPlan(@UserMessage String workoutContext);
}