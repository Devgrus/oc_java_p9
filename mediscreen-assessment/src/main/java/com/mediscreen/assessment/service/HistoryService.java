package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.HistoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HistoryService {

    private final WebClient webClient;

    public HistoryService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://mediscreen-history:8082/patHistory").build();
    }

    /**
     * Get histories by patient id
     * @param id patient id
     * @return history list
     */
    Flux<HistoryDto> getHistoriesByPatId(int id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("patId", id).build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new IllegalArgumentException("WRONG ID")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("External API server error")))
                .bodyToFlux(HistoryDto.class);
    }

}
