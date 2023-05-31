package com.duquejo.ScoreGateway.controller;

import com.duquejo.ScoreGateway.dto.AuthenticationRequest;
import com.duquejo.ScoreGateway.dto.AuthenticationResponse;
import com.duquejo.ScoreGateway.service.JwtTokenService;
import com.duquejo.ScoreGateway.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {

  private static final Log logger = LogFactory.getLog(UserController.class);

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Autowired
  private JwtTokenService jwtTokenService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody AuthenticationRequest request
  ) {
    try {
      logger.warn("Authenticating...");
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()
          )
      );
      UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
      String jwt = jwtTokenService.generateToken( userDetails );
      return new ResponseEntity<>(
          new AuthenticationResponse(jwt),
          HttpStatus.OK
      );
    }catch (BadCredentialsException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
}
