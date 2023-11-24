package com.m19y.render.testrender.service;

import com.m19y.render.testrender.entity.Comment;
import com.m19y.render.testrender.entity.User;
import com.m19y.render.testrender.model.CommentResponse;
import com.m19y.render.testrender.model.CreateUserRequest;
import com.m19y.render.testrender.model.UserResponse;
import com.m19y.render.testrender.model.UserUpdateRequest;
import com.m19y.render.testrender.repository.CommentRepository;
import com.m19y.render.testrender.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final CommentRepository commentRepository;

  @Autowired
  public UserServiceImpl(UserRepository repository, CommentRepository commentRepository) {
    this.repository = repository;
    this.commentRepository = commentRepository;
  }


  @Override
  public void createUser(CreateUserRequest request) {

    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());

    repository.save(user);
  }

  @Override
  public void deleteUser(Integer id) {
    User user = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nda ada user begitu anj!"));

    repository.delete(user);
  }

  @Override
  public void updateUser(UserUpdateRequest request) {
    User user = repository.findById(request.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nda ada user begitu anj!"));

    if(Objects.nonNull(request.getName())){
      user.setName(request.getName());
    }

    if(Objects.nonNull(request.getEmail())){
    user.setEmail(request.getEmail());
    }

    repository.save(user);
  }

  @Override
  public List<UserResponse> getUsers() {

    return repository.findAll()
            .stream()
            .map(this::userToUserResponse)
            .collect(Collectors.toList());
  }

  public UserResponse userToUserResponse(User user){
    UserResponse response = new UserResponse();
    response.setId(user.getId());
    response.setName(user.getName());
    response.setEmail(user.getEmail());

    List<CommentResponse> comments = commentRepository.findAllByUser(user)
            .stream()
            .map(this::commentToResponse)
            .collect(Collectors.toList());

    if(comments.size() != 0){
      response.setComments(comments);
    }

    return response;
  }

  public CommentResponse commentToResponse(Comment comment){
    return CommentResponse.builder().comment(comment.getComment()).build();
  }
}
