package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.User;
import com.spasojetijanic.zavrsniprojekat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepo userRepository;

  @Override
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Optional<User> findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public void update(User user) {
    userRepository.findById(user.getId()).ifPresent(thisUser -> {
      if (!userRepository.existsByEmail(user.getEmail())) {
        thisUser.setEmail(user.getEmail());
      }
      userRepository.save(thisUser);
    });
  }

  @Override
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
