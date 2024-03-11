package com.iosProject.iosProject.service.user;

import com.iosProject.iosProject.bo.user.CreateUserRequest;
import com.iosProject.iosProject.bo.user.UpdateUserStatusRequest;
import com.iosProject.iosProject.entity.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(CreateUserRequest createUserRequest);

    void updateUserStatus(Long userId, UpdateUserStatusRequest updateUserStatusRequest);
    List<UserEntity> getAllUsers();
}

