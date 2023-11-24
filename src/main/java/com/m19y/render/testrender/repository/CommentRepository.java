package com.m19y.render.testrender.repository;

import com.m19y.render.testrender.entity.Comment;
import com.m19y.render.testrender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
  List<Comment> findAllByUser(User user);
}
