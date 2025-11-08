package com.github.mareevvladimir.llama_chat_backend.service;

import com.github.mareevvladimir.llama_chat_backend.dto.CompletionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Service
@RequiredArgsConstructor
public class LlamaCompletionService {

    private final WebClient llamaWebClient;

    public Mono<String> completeOnce(String prompt) {
        return llamaWebClient.post()
                .uri("/completion")
                .bodyValue(new CompletionRequest(prompt))
                .retrieve()
                .bodyToMono(String.class);
    }
}

