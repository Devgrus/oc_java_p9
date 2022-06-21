package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.PatientDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.*;

@Service
public class PatientService {

    private final WebClient webClient;

    public PatientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/patient").build();
    }

    /**
     * Get a patient by id
     * @param id patient id
     * @return patient information
     */
    Mono<PatientDto> getPatientById(int id) {
        return webClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new IllegalArgumentException("ID NOT FOUND")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("External API server error")))
                .bodyToMono(PatientDto.class);
    }

    /**
     * Get patient list by family name
     * @param family family name
     * @return patient list
     */
    Flux<PatientDto> getPatientsByFamily(String family) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("family", family).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new IllegalArgumentException("FAMILY NAME NOT FOUND")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("External API server error")))
                .bodyToFlux(PatientDto.class);
    }
}
