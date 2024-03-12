package com.iosProject.iosProject.controllers;

import com.iosProject.iosProject.bo.child.ChildWithNurseryId;
import com.iosProject.iosProject.bo.enrollment.Enrollment;
import com.iosProject.iosProject.entity.EnrollmentEntity;
import com.iosProject.iosProject.service.enrollment.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enroll")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll_child")
    public ResponseEntity<Void> enrollChild(@RequestParam("nurseryId") Long nurseryId, @RequestBody Enrollment enrollment) {
        enrollmentService.enrollChild(nurseryId, enrollment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get_child_enrollment")
    public ResponseEntity<List<ChildWithNurseryId>> getByChildId(@RequestParam("childId") Long childId) {
        List<ChildWithNurseryId> childEnrollments = enrollmentService.getChildAndNurseryIdByChildId(childId);
        return ResponseEntity.ok(childEnrollments);
    }
}