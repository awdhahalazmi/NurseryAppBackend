package com.iosProject.iosProject.controllers.admin;

import com.iosProject.iosProject.entity.UserEntity;
import com.iosProject.iosProject.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> users= userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}

