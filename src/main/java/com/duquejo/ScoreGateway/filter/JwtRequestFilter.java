package com.duquejo.ScoreGateway.filter;

import com.duquejo.ScoreGateway.service.JwtTokenService;
import com.duquejo.ScoreGateway.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenService jwtTokenService;

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain
  ) throws ServletException, IOException {

    // Looking for Bearer auth header
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if( header == null || ! header.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      logger.warn("Authentication failed.");
      return;
    }

    final String token = header.substring(7);
    final String username = jwtTokenService.validateTokenAndGetUsername(token);
    if( username == null ) {
      chain.doFilter(request, response);
      logger.warn("Authentication failed.");
      return;
    }

    final UserDetails userDetails = userService.loadUserByUsername(username);
    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities()
    );
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    logger.warn("Authentication succeeded.");
    chain.doFilter(request, response);
  }
}
