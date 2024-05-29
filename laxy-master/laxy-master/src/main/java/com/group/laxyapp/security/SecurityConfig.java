package com.group.laxyapp.security;

import com.group.laxyapp.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    // 로그인하지 않은 유저들만 접근 가능한 URL
    private static final String[] anonymousUserUrl = {"/user/login", "/user"};

    // 로그인한 유저들만 접근 가능한 URL
    private static final String[] authenticatedUserUrl = {"/user/edit", "/user/delete"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> {
                    authorize
                            .requestMatchers(anonymousUserUrl).anonymous()
                            .requestMatchers(authenticatedUserUrl).authenticated()
                            .requestMatchers("/user/admin/**").hasAuthority("ADMIN")
                            .anyRequest().permitAll();
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling
                            .accessDeniedHandler(new MyAccessDeniedHandler(userRepository))           // 인가 실패
                            .authenticationEntryPoint(new MyAuthenticationEntryPoint()); // 인증 실패
                })
                .formLogin(formLogin -> {
                    formLogin
                            .loginPage("/user/login")      // 로그인 페이지
                            .usernameParameter("loginId")   // 로그인에 사용될 id
                            .passwordParameter("password")  // 로그인에 사용될 password
                            .failureUrl("/user/login?fail")         // 로그인 실패 시 redirect 될 URL => 실패 메세지 출력
                            .successHandler(new MyLoginSuccessHandler(userRepository));    // 로그인 성공 시 실행 될 Handler
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/user/logout")     // 로그아웃 URL
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            .logoutSuccessHandler(new MyLogoutSuccessHandler());
                })
                .build();
    }
}