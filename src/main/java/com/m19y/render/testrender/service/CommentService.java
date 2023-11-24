package com.m19y.render.testrender.service;

import com.m19y.render.testrender.entity.User;
import com.m19y.render.testrender.model.CreateCommentRequest;

public interface CommentService {

  void createComment(CreateCommentRequest request);
  void deleteComment(Integer id);
}
