package com.iosProject.iosProject.service.auth;

import com.iosProject.iosProject.bo.auth.AuthenticationResponse;
import com.iosProject.iosProject.bo.auth.CreateLoginRequest;
import com.iosProject.iosProject.bo.auth.CreateSignupRequest;
import com.iosProject.iosProject.bo.auth.LogoutResponse;
import com.iosProject.iosProject.bo.customUserDetails.CustomUserDetails;
import com.iosProject.iosProject.config.JWTUtil;
import com.iosProject.iosProject.entity.RoleEntity;
import com.iosProject.iosProject.entity.UserEntity;
import com.iosProject.iosProject.repository.RoleRepository;
import com.iosProject.iosProject.repository.UserRepository;
import com.iosProject.iosProject.util.enums.Roles;
import com.iosProject.iosProject.util.exceptions.BodyGuardException;
import com.iosProject.iosProject.util.exceptions.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailService;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private  final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailService, JWTUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void signup(CreateSignupRequest createSignupRequest) {
        RoleEntity roleEntity= roleRepository.findRoleEntityByTitle(Roles.user.name())
                .orElseThrow(() -> new BodyGuardException("no Roles Found"));;
        UserEntity user= new UserEntity();
        user.setName(createSignupRequest.getName());
        user.setUsername(createSignupRequest.getUsername());
        user.setEmail(createSignupRequest.getEmail());
        user.setRole(roleEntity);
        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }


    @Override
    public AuthenticationResponse login(CreateLoginRequest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(),"username");
        requiredNonNull(createLoginRequest.getPassword(),"password");
        String username= createLoginRequest.getUsername().toLowerCase();
        String password= createLoginRequest.getPassword();
        authentication(username,password);
        CustomUserDetails userDetails = userDetailService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer "+ accessToken);
        return response;



    }

    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"Token");

    }
    private void requiredNonNull(Object obj,String name){
        if(obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name+"can not be empty");
        }
    }
    private void authentication(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BodyGuardException e){
            throw new BodyGuardException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw new UserNotFoundException("Incorrect username");
        }
    }
}
