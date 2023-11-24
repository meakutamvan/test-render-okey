package com.m19y.render.testrender.controller;

import com.m19y.render.testrender.model.CreateUserRequest;
import com.m19y.render.testrender.model.UserResponse;
import com.m19y.render.testrender.model.UserUpdateRequest;
import com.m19y.render.testrender.model.WebResponse;
import com.m19y.render.testrender.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

  private final UserServiceImpl userService;

  @Autowired
  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<List<UserResponse>> getUsers(){

    List<UserResponse> users = userService.getUsers();
    return WebResponse.<List<UserResponse>>builder()
            .data(users)
            .message("Success get all users!")
            .build();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> createUser(@RequestBody CreateUserRequest request){

    userService.createUser(request);
    return WebResponse.<String>builder()
            .message("Success create new user!")
            .build();

  }

  @PatchMapping(path = "/{id}",
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> updateUser(@PathVariable("id") Integer id, @RequestBody UserUpdateRequest request){

    request.setId(id);
    userService.updateUser(request);
    return WebResponse.<String>builder()
            .message("Success update user!")
            .build();

  }

  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> deleteUser(@PathVariable("id") Integer id){

    userService.deleteUser(id);
    return WebResponse.<String>builder()
            .message("Success delete user!")
            .build();

  }

}
