package com.iosProject.iosProject.controllers;

import com.iosProject.iosProject.bo.child.Child;
import com.iosProject.iosProject.service.child.ChildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/child")
public class ChildController {
    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerChildForUser(@RequestBody Child child, @RequestParam Long userId) {
        childService.registerChildForUser(child, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Child registered successfully.");
    }

    @GetMapping("/{childId}")
    public ResponseEntity<Child> getChildById(@PathVariable Long childId) {
        Child child = childService.getChildById(childId);
        if (child != null) {
            return ResponseEntity.ok(child);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
