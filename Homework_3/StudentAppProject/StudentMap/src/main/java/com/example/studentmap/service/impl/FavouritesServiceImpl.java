package com.example.studentmap.service.impl;

import com.example.studentmap.model.Favourites;
import com.example.studentmap.model.Location;
import com.example.studentmap.repository.FavouritesRepository;
import com.example.studentmap.repository.LocationRepository;
import com.example.studentmap.repository.UserRepository;
import com.example.studentmap.service.FavouritesService;
import com.example.studentmap.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final LocationService locationService;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public FavouritesServiceImpl(FavouritesRepository favouritesRepository, LocationService locationService,
                                 UserRepository userRepository,
                                 LocationRepository locationRepository) {
        this.favouritesRepository = favouritesRepository;
        this.locationService = locationService;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
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
        Favourites f = null;
        if(favouritesRepository.findFavouritesByUser_Username(username).isPresent()) {
            f = favouritesRepository.findFavouritesByUser_Username(username).get();
        }
        else{
            f = createFavourites(username);
        }
        Location loc = locationService.getLocationById(locationID);
        if(!f.getLocationList().contains(loc)) {
            f.getLocationList().add(loc);
        }
        if(!loc.getFavourites().contains(f))
        {
            loc.getFavourites().add(f);
        }
        return favouritesRepository.save(f);
    }

    @Override
    public Favourites createFavourites(String username) {
        return new Favourites(userRepository.findByUsername(username).get());
    }


    @Override
    public void deleteById(Long id) {
        this.favouritesRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<Favourites> favourites) {
        favouritesRepository.saveAll(favourites);
    }
}
