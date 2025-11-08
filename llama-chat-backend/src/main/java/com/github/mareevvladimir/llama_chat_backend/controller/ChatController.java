package com.github.mareevvladimir.llama_chat_backend.controller;

import com.github.mareevvladimir.llama_chat_backend.service.LlamaCompletionService;
import com.github.mareevvladimir.llama_chat_backend.service.LlamaStreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final LlamaStreamingService llamaStreamingService;
    private final LlamaCompletionService llamaCompletionService;

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam String prompt) {
        return llamaStreamingService.streamCompletion(prompt);
    }

    @GetMapping("/simple")
    public Mono<String> simple(@RequestParam String prompt) {
        return llamaCompletionService.completeOnce(prompt);
    }

}
