package com.np.bank.management.configuration;

import com.np.bank.management.utilities.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!(request.getRequestURI().contains("/h2")||request.getRequestURI().contains("public"))) {
            System.out.println("Not Public URI");
            if(request.getHeader("JWT")==null
                    ||request.getHeader("JWT").isEmpty()
                    ||request.getHeader("JWT").isBlank()
                    ||!(jwtUtility.validateToken(request.getHeader("JWT")))) {
                System.out.println("InValid Token");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("JWT Token Expired or not present");
                return;
            }

            String jwt = request.getHeader("JWT");
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtility.extractUsername(jwt));
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                    userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
