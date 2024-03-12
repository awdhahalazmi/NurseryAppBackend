package com.iosProject.iosProject.controllers;

import com.iosProject.iosProject.bo.nursery.Nursery;
import com.iosProject.iosProject.entity.NurseryEntity;
import com.iosProject.iosProject.service.nursery.NurseryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nurseries")
public class NurseryController {

    private final NurseryService nurseryService;

    @Autowired
    public NurseryController(NurseryService nurseryService) {
        this.nurseryService = nurseryService;
    }

    @PostMapping("/admin/add_nursery")
    public ResponseEntity<Void> registerNursery(@RequestBody Nursery nursery) {
        nurseryService.registerNursery(nursery);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get_nurseries")
    public ResponseEntity<List<NurseryEntity>> getNurseriesByCaseAndArea(@RequestParam(required = false) Long caseId,
                                                                         @RequestParam(required = false) Long areaId) {
        List<NurseryEntity> nurseries = nurseryService.getNurseriesByCaseAndArea(caseId, areaId);
        return ResponseEntity.ok(nurseries);
    }
}