package com.mediscreen.history.controller;

import com.mediscreen.history.domain.History;
import com.mediscreen.history.dto.HistoryDto;
import com.mediscreen.history.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Get all histories of a patient
     * @param patId patient id
     * @return all histories
     */
    @GetMapping()
    public ResponseEntity<List<HistoryDto>> getHistories(@RequestParam int patId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(historyService.getHistories(patId).stream()
                        .map(history -> HistoryDto.builder()
                                .id(history.getId())
                                .patId(history.getPatId())
                                .note(history.getNote())
                                .createdDate(history.getCreatedDate())
                                .lastModifiedDate(history.getLastModifiedDate())
                                .build()).collect(Collectors.toList()));
    }

    /**
     * Update a history
     * @param dto history information
     * @return history information
     */
    @PutMapping("/update")
    public ResponseEntity<HistoryDto> updateHistory(@Valid @RequestBody HistoryDto dto) {
        History history = historyService.updateHistory(dto.toEntity());
        return ResponseEntity.status(HttpStatus.OK)
                .body(HistoryDto.builder()
                        .id(history.getId())
                        .patId(history.getPatId())
                        .note(history.getNote())
                        .createdDate(history.getCreatedDate())
                        .lastModifiedDate(history.getLastModifiedDate())
                        .build());
    }

    /**
     * Delete a history
     * @param id history id
     * @return no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable String id) {
        historyService.deleteHistory(id);
        return ResponseEntity.noContent().header("Content-Length", "0").build();
    }
}
