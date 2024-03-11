package com.iosProject.iosProject.controllers;

import com.iosProject.iosProject.entity.AreaEntity;
import com.iosProject.iosProject.service.area.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/area")
public class AreaController {

    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/get_areas")
    public ResponseEntity<List<AreaEntity>> getAllAreas() {
        List<AreaEntity> areas = areaService.getAllAreas();
        return ResponseEntity.ok(areas);
    }

    @PostMapping("/admin/add_area")
    public ResponseEntity<AreaEntity> addArea(@RequestBody AreaEntity area) {
        try {
            AreaEntity addedArea = areaService.addArea(area);
            return new ResponseEntity<>(addedArea, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}