package com.m19y.render.testrender.service;

import com.m19y.render.testrender.entity.Comment;
import com.m19y.render.testrender.entity.User;
import com.m19y.render.testrender.model.CreateCommentRequest;
import com.m19y.render.testrender.repository.CommentRepository;
import com.m19y.render.testrender.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentServiceImpl implements CommentService {

  private final UserRepository userRepository;
  private final CommentRepository repository;

  @Autowired
  public CommentServiceImpl(UserRepository userRepository, CommentRepository repository) {
    this.userRepository = userRepository;
    this.repository = repository;
  }
  @Override
  public void createComment(CreateCommentRequest request) {

    User user = userRepository.findById(request.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nda ada user begitu anj!"));

    Comment comment = new Comment();
    comment.setComment(request.getComment());

    comment.setUser(user);

    repository.save(comment);
  }

  @Override
  public void deleteComment(Integer id) {
    Comment comment = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nda ada comment begitu anu"));

    repository.delete(comment);
  }
}
