package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.dto.AssessmentDto;
import com.mediscreen.assessment.service.AssessmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assess")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    /**
     * Get a diabetes assessment by patient id
     * @param id patient id
     * @return assessment result
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<AssessmentDto> getAssessmentById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assessmentService.diabetesAssessmentByPatId(id));
    }

    /**
     * Get a diabetes assessment by family name
     * @param family family name
     * @return assessment list
     */
    @GetMapping("/family/{family}")
    public ResponseEntity<List<AssessmentDto>> getAssessmentByFamily(@PathVariable String family) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assessmentService.diabetesAssessmentByFamilyName(family.toLowerCase()));
    }
}
