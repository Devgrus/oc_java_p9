package com.mediscreen.history.service;

import com.mediscreen.history.domain.History;
import com.mediscreen.history.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    /**
     * Add patient history
     * @param history patient history information
     * @return patient history information
     */
    public History addHistory(History history) {
        return historyRepository.save(history);
    }

    /**
     * Get all histories of patient
     * @param patId patient id
     * @return all histories
     */
    public List<History> getHistories(int patId) {
        return historyRepository.findAllByPatId(patId);
    }
}
