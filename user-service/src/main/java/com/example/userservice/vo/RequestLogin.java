package com.example.userservice.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestLogin {

  @NotNull(message = "Email cannot be null")
  @Size(min = 2, message = "Email not be less than two")
  @Email
  private String email;

  @NotNull(message = "password cannot be null")
  @Size(min = 8, message = "password must 8 upper")
  private String password;

}
