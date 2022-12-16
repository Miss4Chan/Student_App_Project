package com.example.studentmap.service.impl;

import com.example.studentmap.model.Comment;
import com.example.studentmap.model.Location;
import com.example.studentmap.model.User;
import com.example.studentmap.repository.CommentRepository;
import com.example.studentmap.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(String comment, User user, Location location){
        commentRepository.save(new Comment(comment,user,location));
    }

    @Override
    public void updateComment(String comment, User user, Location location){
        Comment c = commentRepository.findByLocation_IdAndUser_Username(location.getId(),user.getUsername());
        c.setComment(comment);
        commentRepository.save(c);
    }

    @Override
    public List<Comment> getAll(){
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id){
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAllCommentsByLocation_Id(Long id){
        return commentRepository.findByLocation_Id(id);
    }

    @Override
    public List<Comment> getAllCommentsByUser_Username(String username){
        return commentRepository.findByUser_username(username);
    }
}
