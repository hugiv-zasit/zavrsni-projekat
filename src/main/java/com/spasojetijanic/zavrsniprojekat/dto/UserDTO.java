package com.spasojetijanic.zavrsniprojekat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;

  @NotBlank
  private String username;

  @NotBlank
  @Email
  private String email;

  private String password;

  private Date joiningDate;
}
