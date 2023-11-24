package com.m19y.render.testrender.controller;

import com.m19y.render.testrender.model.CreateCommentRequest;
import com.m19y.render.testrender.model.WebResponse;
import com.m19y.render.testrender.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users/comment")
public class CommentController {

  private final CommentServiceImpl service;

  @Autowired
  public CommentController(CommentServiceImpl service) {
    this.service = service;
  }

  @PostMapping(path = "/{id}",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> createComment(@PathVariable("id") Integer id,
                                           @RequestBody CreateCommentRequest request){

    request.setId(id);
    service.createComment(request);
    return WebResponse.<String>builder()
            .message("Success create new comment")
            .build();
  }

  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> deleteComment(@PathVariable("id") Integer id){

    service.deleteComment(id);
    return WebResponse.<String>builder()
            .message("Success delete comment")
            .build();
  }
}
