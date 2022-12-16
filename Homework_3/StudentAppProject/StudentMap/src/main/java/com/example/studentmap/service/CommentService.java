package com.example.studentmap.service;

import com.example.studentmap.model.Comment;
import com.example.studentmap.model.Location;
import com.example.studentmap.model.User;

import java.util.List;

public interface CommentService{
    void deleteById(Long id);
    Comment getCommentById(Long id);
    void createComment(String comment, User user, Location location);
    void updateComment(String comment, User user, Location location);
    List<Comment> getAll();
    List<Comment> getAllCommentsByLocation_Id(Long id);
    List<Comment> getAllCommentsByUser_Username(String username);
}
