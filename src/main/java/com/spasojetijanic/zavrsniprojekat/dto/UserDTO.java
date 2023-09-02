package com.spasojetijanic.zavrsniprojekat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

  private Long userId;

  @NotBlank
  private String username;

  @NotBlank
  @Email
  private String email;

  private String password;

  private Date joiningDate;
}
