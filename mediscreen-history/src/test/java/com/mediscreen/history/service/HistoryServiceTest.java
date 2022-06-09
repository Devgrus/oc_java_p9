package com.mediscreen.history.service;

import com.mediscreen.history.domain.History;
import com.mediscreen.history.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

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

}
