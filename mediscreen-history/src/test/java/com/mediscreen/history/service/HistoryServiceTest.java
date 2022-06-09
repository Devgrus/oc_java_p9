package com.mediscreen.history.service;

import com.mediscreen.history.domain.History;
import com.mediscreen.history.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {

    @Mock
    HistoryRepository historyRepository;

    @InjectMocks
    HistoryService historyService;

    @Test
    public void addHistoryTest() {
        //given
        History history1 = History.builder()
                .patId(1)
                .note("NOTE NOTE NOTE")
                .build();

        //when
        when(historyRepository.save(history1)).thenReturn(history1);

        //then
        assertThat(historyService.addHistory(history1)).isEqualTo(history1);
    }

    @Test
    public void getHistoriesTest() {
        //given
        History history1 = History.builder()
                .id("asdfasdf")
                .patId(1)
                .note("NOTE NOTE NOTE")
                .createdDate(LocalDate.of(2020, 4, 20))
                .build();

        History history2 = History.builder()
                .id("qwerqwer")
                .patId(1)
                .note("NOTE NOTE NOTE")
                .createdDate(LocalDate.of(2020, 6, 20))
                .build();

        List<History> histories = new ArrayList<>();
        histories.add(history1);
        histories.add(history2);

        //when
        when(historyRepository.findAllByPatId(history1.getPatId())).thenReturn(histories);

        //then
        assertThat(historyService.getHistories(history1.getPatId())).isEqualTo(histories);
    }

    @Test
    public void updateHistoryTest() {
        //given
        History history1 = History.builder()
                .id("asdfasdf")
                .patId(1)
                .note("NOTE NOTE NOTE")
                .build();

        History history2 = History.builder()
                .id("asdfasdf")
                .patId(1)
                .note("NOTE NOTE")
                .build();

        //when
        when(historyRepository.findById(history1.getId())).thenReturn(Optional.of(history1));

        //then
        assertThat(historyService.updateHistory(history2).getNote()).isEqualTo(history2.getNote());
    }

    @Test
    public void updateHistoryTestHistoryNotFound() {
        //given
        History history1 = History.builder()
                .id("asdfasdf")
                .patId(1)
                .note("NOTE NOTE NOTE")
                .build();

        History history2 = History.builder()
                .id("asdfasdf")
                .patId(1)
                .note("NOTE NOTE")
                .build();

        //when
        when(historyRepository.findById(history1.getId())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> historyService.updateHistory(history2)).hasMessage("HISTORY NOT FOUND");
    }

    @Test
    public void deleteHistoryTestNotFound() {
        //given
        String historyId = "asdfasdf";

        //when
        when(historyRepository.findById(historyId)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> historyService.deleteHistory(historyId)).hasMessage("HISTORY NOT FOUND");
    }

}
