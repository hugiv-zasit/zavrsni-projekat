package com.spasojetijanic.zavrsniprojekat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "doctor")
  private Set<Appointment> appointments;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id")
  private User user;
}
