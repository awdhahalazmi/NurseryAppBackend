package com.iosProject.iosProject.service.auth;

import com.iosProject.iosProject.bo.auth.AuthenticationResponse;
import com.iosProject.iosProject.bo.auth.CreateLoginRequest;
import com.iosProject.iosProject.bo.auth.CreateSignupRequest;
import com.iosProject.iosProject.bo.auth.LogoutResponse;

public interface AuthService {
    void signup(CreateSignupRequest createSignupRequest);


    AuthenticationResponse login(CreateLoginRequest createLoginRequest);
    void logout(LogoutResponse logoutResponse);
}
