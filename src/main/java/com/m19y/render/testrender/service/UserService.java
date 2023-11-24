package com.m19y.render.testrender.service;

import com.m19y.render.testrender.model.CreateUserRequest;
import com.m19y.render.testrender.model.UserResponse;
import com.m19y.render.testrender.model.UserUpdateRequest;

import java.util.List;

public interface UserService {

  void createUser(CreateUserRequest request);

  void deleteUser(Integer id);

  void updateUser(UserUpdateRequest request);

  List<UserResponse> getUsers();
}
