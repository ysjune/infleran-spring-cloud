package com.example.userservice.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUser {

  @NotNull(message = "email not null")
  @Size(min = 2, message = "email minimum 2 letter")
  private String email;
  @NotNull(message = "name not null")
  @Size(min = 2, message = "name minimum 2 letter")
  private String name;
  @NotNull(message = "password not null")
  @Size(min = 2, message = "password minimum 2 letter")
  private String password;
}
