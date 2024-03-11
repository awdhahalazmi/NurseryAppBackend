package com.iosProject.iosProject.config;

import com.iosProject.iosProject.service.auth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final  String AUTH_PATH= "/api/v1/auth/**";
    public static final String USER_PATH = "/api/v1/user/**";
    public static final String ADMIN_PATH= "/api/v1/admin/**";

    public static final String Child_PATH = "/api/v1/child/**";

    public static final String ADMIN_AREA_PATH= "/api/v1/admin/area/**";
    public static final String ADMIN_CASE_PATH= "/api/v1/cases/admin/add_case/**";
    public static final String USER_CASE_PATH= "/api/v1/cases/get_case/**";
   public static final String USER_AREA_PATH= "/api/v1/area/**";
//    public static final String CHAPTER_PATH= "/api/v1/chapter/**";
//    public static final String Lesson_PATH= "/api/v1/lesson/**";
//    public static final String PROGRESS_PATH= "/api/v1/progress/**";


    private static final List<String> ALLOWED_METHODS = Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH");
    private static final List<String> ALLOWED_HEADERS = Arrays.asList("x-requested-with", "authorization", "Content-Type",
            "Authorization", "credential", "X-XSRF-TOKEN", "X-Refresh-Token", "X-Client-Id", "x-client-id");

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .configurationSource(request -> getCorsConfiguration())
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_PATH).permitAll()
                .antMatchers(Child_PATH).hasAuthority("user")
//                .antMatchers(CHAPTER_PATH).hasAuthority("user")
//                .antMatchers(Lesson_PATH).hasAuthority("user")
                .antMatchers(USER_PATH).hasAuthority("user")
                .antMatchers(ADMIN_PATH).hasAuthority("admin")
                .antMatchers(ADMIN_AREA_PATH).hasAuthority("admin")
                .antMatchers(USER_AREA_PATH).hasAuthority("user")
                .antMatchers(ADMIN_CASE_PATH).hasAuthority("admin")
                .antMatchers(USER_CASE_PATH).hasAuthority("user")
//                .antMatchers(ADMIN_QUESTION_PATH).hasAuthority("admin")
//                .antMatchers(USER_QUESTION_PATH).hasAuthority("user")
//                .antMatchers(PROGRESS_PATH).hasAuthority("user")
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/configuration/**", "/webjars/**", "/favicon-**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(new JWTAuthFilter(jwtUtil,userDetailsService), UsernamePasswordAuthenticationFilter.class);

    }

    private CorsConfiguration getCorsConfiguration(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(ALLOWED_HEADERS);
        corsConfiguration.setAllowedMethods(ALLOWED_METHODS);
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);

        return corsConfiguration;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}



