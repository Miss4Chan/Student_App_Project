package finki.ukim.mk.studentmap.repository;

import finki.ukim.mk.studentmap.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
<<<<<<< HEAD
    List<Comment> findByUser_username(String username);

    List<Comment> findByLocation_Id(Long id);

    Comment findByLocation_IdAndUser_Username(Long id, String username);
=======
    List<Comment> findByLocation_Id(Long id);
>>>>>>> origin/main
}
