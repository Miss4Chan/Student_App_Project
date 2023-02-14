package finki.ukim.mk.studentmap.service.impl;

import finki.ukim.mk.studentmap.model.Comment;
import finki.ukim.mk.studentmap.model.Location;
import finki.ukim.mk.studentmap.model.User;
import finki.ukim.mk.studentmap.repository.CommentRepository;
import finki.ukim.mk.studentmap.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(String comment, User user, Location location) {
        commentRepository.save(new Comment(comment, user, location));
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAllCommentsByLocationId(Long id) {
        return commentRepository.findByLocation_Id(id);
    }
}
