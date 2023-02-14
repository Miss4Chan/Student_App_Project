package finki.ukim.mk.studentmap.repository;

import finki.ukim.mk.studentmap.model.Favourites;
import finki.ukim.mk.studentmap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
<<<<<<< HEAD
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    Optional<Favourites> findByUser(User user);

=======
public interface FavouritesRepository extends JpaRepository<Favourites,Long> {
>>>>>>> origin/main
    Optional<Favourites> findFavouritesByUser_Username(String username);

}
