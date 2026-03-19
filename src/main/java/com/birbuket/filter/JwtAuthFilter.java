package com.birbuket.filter;

import com.birbuket.service.CustomUserDetailsService;
import com.birbuket.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Header-dən token-i götür
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. "Bearer " hissəsini kəs, yalnız token-i götür
        String token = authHeader.substring(7);

        // 3. Token-dən username oxu
        String username = jwtService.extractUsername(token);

        // 4. Username var və hələ authenticate olunmayıb?
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // Əgər bu request artıq authenticate olunubsa — təkrar yoxlama etmirik. Performans üçündür.

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 5. Token valid-dir?
            if (jwtService.isTokenValid(token, userDetails)) { // İki şeyi yoxlayır: username uyğundur + vaxtı keçməyib.

                // 6. SecurityContext-ə əlavə et
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}