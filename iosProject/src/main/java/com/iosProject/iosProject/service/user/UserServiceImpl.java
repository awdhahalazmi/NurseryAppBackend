package com.iosProject.iosProject.service.user;

import com.iosProject.iosProject.bo.user.CreateUserRequest;
import com.iosProject.iosProject.bo.user.UpdateUserStatusRequest;
import com.iosProject.iosProject.entity.UserEntity;
import com.iosProject.iosProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity= new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userRepository.save(userEntity);

    }

    @Override
    public void updateUserStatus(Long userId, UpdateUserStatusRequest updateUserStatusRequest) {
        UserEntity userEntity=userRepository.findById(userId).orElseThrow();
        if (!updateUserStatusRequest.getStatus().equals("ACTIVE") && !updateUserStatusRequest.getStatus().equals("INACTIVE")){
            throw new IllegalArgumentException("Error. The status must be either ACTIVE or INACTIVE");
        }
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}

