package com.spasojetijanic.zavrsniprojekat.controller;

import com.spasojetijanic.zavrsniprojekat.converter.UserDTOConverter;
import com.spasojetijanic.zavrsniprojekat.dto.UserDTO;
import com.spasojetijanic.zavrsniprojekat.model.User;
import com.spasojetijanic.zavrsniprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private UserDTOConverter userDTOConverter;

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') || #id = authentication.principal.id")
  public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
    Optional<User> user = userService.findById(id);
    return user.map(u -> ResponseEntity.ok(userDTOConverter.convertToDto(u)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/username")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserDTO> findByUsername(@RequestParam String username) {
    Optional<User> user = userService.findUserByUsername(username);
    return user.map(u -> ResponseEntity.ok(userDTOConverter.convertToDto(u)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/email")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
    Optional<User> user = userService.findByEmail(email);
    return user.map(u -> ResponseEntity.ok(userDTOConverter.convertToDto(u)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> users = userService.findAll();
    return new ResponseEntity<>(users.stream()
        .map(u -> userDTOConverter.convertToDto(u))
        .collect(Collectors.toList()), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') || #id = authentication.principal.id")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
