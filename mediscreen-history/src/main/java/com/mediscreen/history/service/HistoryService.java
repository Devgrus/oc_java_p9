package com.mediscreen.history.service;

import com.mediscreen.history.domain.History;
import com.mediscreen.history.repository.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * Get a history by id
     * @param id history id
     * @return history
     */
    public History findById(String id) {
        return historyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("HISTORY NOT FOUND"));
    }

    /**
     * Update history
     * @param history history information
     * @return history information
     */
    @Transactional
    public History updateHistory(History history) {
        System.out.println(history.getId());
        History result = findById(history.getId());
        result.setNote(history.getNote());
        return result;
    }

    /**
     * Delete a history
     * @param id history id
     */
    public void deleteHistory(String id) {
        historyRepository.delete(findById(id));
    }
}
