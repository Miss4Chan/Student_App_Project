package com.example.studentmap.repository;

import com.example.studentmap.model.Comment;
import com.example.studentmap.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByUser_username(String username);
    List<Comment> findByLocation_Id(Long id);
    Comment findByLocation_IdAndUser_Username(Long id, String username);
}
