package com.example.studentmap.service.impl;

import com.example.studentmap.model.Favourites;
import com.example.studentmap.model.Location;
import com.example.studentmap.repository.FavouritesRepository;
import com.example.studentmap.service.FavouritesService;
import com.example.studentmap.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final LocationService locationService;

    public FavouritesServiceImpl(FavouritesRepository favouritesRepository, LocationService locationService) {
        this.favouritesRepository = favouritesRepository;
        this.locationService = locationService;
    }

    @Override
    public Optional<Favourites> getFave(String username) {
        return favouritesRepository.findFavouritesByUser_Username(username);
    }

    @Override
    public List<Location> listAllFaves(Long favesID) {
        Optional<Favourites> favourites = favouritesRepository.findById(favesID);
        return favourites.map(Favourites::getLocationList).orElse(null);
    }

    @Override
    public Favourites addLocationToFaves(String username, Long locationID) {
        Favourites f = favouritesRepository.findFavouritesByUser_Username(username).get();
        f.getLocationList().add(locationService.getLocationById(locationID));
        return favouritesRepository.save(f);
    }
}
