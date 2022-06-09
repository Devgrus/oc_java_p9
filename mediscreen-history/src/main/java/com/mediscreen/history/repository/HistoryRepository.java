package com.mediscreen.history.repository;

import com.mediscreen.history.domain.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {
    List<History> findAllByPatId(int patId);
}
