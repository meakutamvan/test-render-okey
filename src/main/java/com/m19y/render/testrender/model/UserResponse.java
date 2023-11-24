package com.m19y.render.testrender.model;

import com.m19y.render.testrender.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

  private Integer id;
  private String name;

  private String email;

  private List<CommentResponse> comments;
}
