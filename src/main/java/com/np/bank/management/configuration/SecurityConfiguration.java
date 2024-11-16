package com.np.bank.management.configuration;

import com.np.bank.management.exceptionHandling.APIAccessDeniedHandler;
import com.np.bank.management.exceptionHandling.APIAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.securityContext(context -> context.requireExplicitSave(true));

        http.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler()));
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()));

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/public/**", "/h2/**").permitAll()
                .requestMatchers("/accounts/**").hasRole("CUSTOMER")
                .requestMatchers("/managers/**").hasRole("MANAGER")
                .requestMatchers("/admin/contact").hasAuthority("EMPLOYEE")
                .requestMatchers("/superAdmins/contact").hasAuthority("ADMIN")
        );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder encoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new APIAccessDeniedHandler();
    }

    @Bean
    APIAuthenticationEntryPoint authenticationEntryPoint() {
        return new APIAuthenticationEntryPoint();
    }
}
