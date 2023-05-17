package com.itransition.backend.authencation.jwt;

import com.itransition.backend.util.Constants;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        String username = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(Constants.BEARER + Constants.SPACE)) {
            token = authorizationHeader.substring(7);
            try {
                username = jwtTokenUtil.extractUsername(token);
            } catch (SignatureException exception) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(token, userDetails)) {
                String requestURI = request.getRequestURI();
                if (jwtTokenUtil.isRefreshToken(token) && requestURI.contains(Constants.REFRESH_TOKEN)
                        && request.getMethod().equals(HttpMethod.GET.name())) {
                    request.setAttribute(Constants.TOKEN, token);
                }
                createAuthenticationToken(request, userDetails);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void createAuthenticationToken(HttpServletRequest request, UserDetails userDetails) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

}
