package com.github.mareevvladimir.llama_chat_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class LlamaStreamingService {

    private final WebClient llamaWebClient;

    public Flux<String> streamCompletion(String prompt) {
        return llamaWebClient.post()
                .uri("/completion")
                .bodyValue("{\"prompt\": \"" + prompt + "\"}") // JSON string payload
                .retrieve()
                .bodyToFlux(String.class)
                .map(chunk -> chunk.replace("\n", "")) // clean formatting
                .filter(chunk -> !chunk.isBlank())
                .delayElements(Duration.ofMillis(20)); // smooth stream spacing (optional)
    }
}

