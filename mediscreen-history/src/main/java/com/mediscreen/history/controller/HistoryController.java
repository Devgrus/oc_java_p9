package com.mediscreen.history.controller;

import com.mediscreen.history.domain.History;
import com.mediscreen.history.dto.HistoryDto;
import com.mediscreen.history.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/patHistory")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    /**
     * Add patient history
     * @param dto patient history
     * @return patient history
     */
    @PostMapping("/add")
    public ResponseEntity<HistoryDto> addHistory(@Valid @RequestBody HistoryDto dto) {
        History history = historyService.addHistory(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(HistoryDto.builder()
                        .id(history.getId())
                        .patId(history.getPatId())
                        .note(history.getNote())
                        .createdDate(history.getCreatedDate())
                        .build());
    }
}
