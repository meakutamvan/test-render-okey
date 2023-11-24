package com.m19y.render.testrender.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

  private String name;

  private String email;
}
