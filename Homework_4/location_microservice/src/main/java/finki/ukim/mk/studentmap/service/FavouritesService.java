package finki.ukim.mk.studentmap.service;

import finki.ukim.mk.studentmap.model.Favourites;
import finki.ukim.mk.studentmap.model.Location;

import java.util.List;
import java.util.Optional;

public interface FavouritesService {
    Optional<Favourites> getFave(String username);
    List<Location> listAllFaves(Long favesID);
    Favourites addLocationToFaves(String username, Long favesID);
    Favourites createFavourites(String username);
    void saveAll(List<Favourites> favourites);
}
