package com.example.studentmap.service;

import com.example.studentmap.model.Favourites;
import com.example.studentmap.model.Location;

import java.util.List;
import java.util.Optional;

public interface FavouritesService {
    Optional<Favourites> getFave(String username);
    List<Location>  listAllFaves(Long favesID);
    Favourites addLocationToFaves(String username, Long favesID);
    Favourites createFavourites(String username);
}

