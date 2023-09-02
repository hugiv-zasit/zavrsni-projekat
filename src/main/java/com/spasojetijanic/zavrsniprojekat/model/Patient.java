package com.spasojetijanic.zavrsniprojekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private BloodGroup bloodGroup;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private float height;

  private float weight;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
  private Set<Appointment> appointments;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;
}
