package com.example.studentmap.repository;

import com.example.studentmap.model.Comment;
import com.example.studentmap.model.Favourites;
import com.example.studentmap.model.Location;
import com.example.studentmap.model.User;
import com.example.studentmap.service.FavouritesService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites,Long> {
    Optional<Favourites> findByUser(User user);
    Optional<Favourites> findFavouritesByUser_Username(String username);

}
