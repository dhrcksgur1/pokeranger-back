package io.elice.pokeranger.global.config;

import io.elice.pokeranger.global.jwt.JwtSecurityConfig;
import io.elice.pokeranger.global.jwt.TokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    // tokenProvide <- 무조건 필요해 보인다.
    private final TokenProvider tokenProvider;

    // 동시성은 일단 제외
    // private final CorsFilter corsFilter;

    // 인증 실패관련, 일단 제외
    // private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    // private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Autowired
    public SecurityConfig(
            TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // 필터체인이 필수
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf(AbstractHttpConfigurer::disable) // 팀3: 이건 공통

                // cors 적용을 일단안하기때문에 제외하자
                // .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//            .exceptionHandling(exceptionHandling -> exceptionHandling
//                .accessDeniedHandler(jwtAccessDeniedHandler)
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//            )

                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        // 최상위 경로를 적용하도록 하자 ,
                        .requestMatchers("/categories", "/api/signup").permitAll() //인증없이 허용 .
                      //  .anyRequest().authenticated()  // 위에 permitAll 말고는 다 인증받자.
                        .anyRequest().permitAll()
                )


                // 이건 적용필요
                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )



                .with(new JwtSecurityConfig(tokenProvider), customizer -> {});
        return http.build();
    }



}