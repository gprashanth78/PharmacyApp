package com.pharmacy.config;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectURL = null;

        Collection<String> authorities = authentication.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());

        System.out.println("Authorities: " + authorities);

        if (authorities.contains("ROLE_Admin")) {
            redirectURL = "/admin/dashboard";
        } else if (authorities.contains("ROLE_User")) {
            redirectURL = "/user/dashboard";
        }

        System.out.println("Redirect URL: " + redirectURL);

        if (redirectURL != null) {
            response.sendRedirect(redirectURL);
        } else {
            throw new IllegalStateException("No suitable target URL found for user role");
        }
    }
}
