package com.spasojetijanic.zavrsniprojekat.repository;

import com.spasojetijanic.zavrsniprojekat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);
}
