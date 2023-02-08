package finki.ukim.mk.studentmap.service;

import finki.ukim.mk.studentmap.model.Comment;
import finki.ukim.mk.studentmap.model.Location;
import finki.ukim.mk.studentmap.model.User;

import java.util.List;

public interface CommentService{
    void deleteById(Long id);
    void createComment(String comment, User user, Location location);
    List<Comment> getAll();
    List<Comment> getAllCommentsByLocationId(Long id);
}
