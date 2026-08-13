package com.pulsefit.pulsefit.ai;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

@Configuration
public class AiConfig {

    @Value("${GROQ_API_KEY:}")
    private String groqApiKey;

    @Value("${langchain4j.ollama.chat-model.base-url:http://localhost:11434}")
    private String ollamaBaseUrl;

    @Value("${langchain4j.ollama.chat-model.model-name:llama3.2}")
    private String ollamaModelName;

    @Bean
    @Primary
    public ChatLanguageModel chatLanguageModel() {
        if (groqApiKey != null && !groqApiKey.trim().isEmpty()) {
            return OpenAiChatModel.builder()
                    .baseUrl("https://api.groq.com/openai/v1")
                    .apiKey(groqApiKey)
                    .modelName("llama-3.3-70b-versatile")
                    .temperature(0.7)
                    .timeout(Duration.ofSeconds(30))
                    .build();
        }

        return OllamaChatModel.builder()
                .baseUrl(ollamaBaseUrl)
                .modelName(ollamaModelName)
                .temperature(0.7)
                .timeout(Duration.ofSeconds(60))
                .build();
    }
}