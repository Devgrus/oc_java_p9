package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.dto.AssessmentDto;
import com.mediscreen.assessment.enums.RiskLevel;
import com.mediscreen.assessment.service.AssessmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AssessmentController.class)
public class AssessmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AssessmentService assessmentService;

    @Test
    public void getAssessmentById() throws Exception {
        //given
        int id = 1;

        AssessmentDto result = AssessmentDto.builder()
                .family("ferguson")
                .given("lucas")
                .age(51)
                .diabetesAssessment(RiskLevel.None)
                .build();

        //when
        when(assessmentService.diabetesAssessmentByPatId(id)).thenReturn(result);

        //then
        mockMvc.perform(get("/assess/id/" + id))
                .andExpect(status().isOk());
    }
}