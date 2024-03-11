package com.iosProject.iosProject.controllers;


import com.iosProject.iosProject.entity.AreaEntity;
import com.iosProject.iosProject.entity.CaseEntity;
import com.iosProject.iosProject.service.area.AreaService;
import com.iosProject.iosProject.service.childCase.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cases")
public class CaseController {

    private final CaseService caseService;

    @Autowired
    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/get_cases")
    public ResponseEntity<List<CaseEntity>> getAllCases() {
        List<CaseEntity> cases = caseService.getAllCases();
        return new ResponseEntity<>(cases, HttpStatus.OK);
    }

    @PostMapping("/admin/add_case")
    public ResponseEntity<CaseEntity> addCase(@RequestBody CaseEntity caseEntity) {
        CaseEntity addedCase = caseService.addCase(caseEntity);
        return new ResponseEntity<>(addedCase, HttpStatus.CREATED);
    }
}
