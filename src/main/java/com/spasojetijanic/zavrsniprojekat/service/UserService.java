package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  Optional<User> findById(Long id);

  Optional<User> findUserByEmail(String email);

  Optional<User> findUserByUsername(String username);

  Optional<User> findByEmail(String email);

  List<User> findAll();

  void update(User user);

  void deleteById(Long id);
}
