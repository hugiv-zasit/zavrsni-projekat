package com.spasojetijanic.zavrsniprojekat.security.controller;

import com.spasojetijanic.zavrsniprojekat.repository.UserRepo;
import com.spasojetijanic.zavrsniprojekat.security.model.AuthenticationRequest;
import com.spasojetijanic.zavrsniprojekat.security.model.AuthenticationResponse;
import com.spasojetijanic.zavrsniprojekat.security.model.RegisterRequest;
import com.spasojetijanic.zavrsniprojekat.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final UserRepo userRepository;

  @PostMapping("/register")
  public ResponseEntity<?> register(
      @RequestBody RegisterRequest request
  )  {
    if(userRepository.existsByEmail(request.getEmail())) {
      return new ResponseEntity<>("Email already exists!", HttpStatus.BAD_REQUEST);
    }
    if(userRepository.existsByUsername(request.getUsername())) {
      return new ResponseEntity<>("Username already exists!", HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
