package com.spasojetijanic.zavrsniprojekat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  @NotBlank
  private String username;

  @Column(nullable = false)
  @NotBlank
  private String password;

  @Column(unique = true, nullable = false)
  @NotBlank
  @Email
  private String email;

  private Date joiningDate;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
  private Doctor doctor;

  @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
  private Patient patient;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
