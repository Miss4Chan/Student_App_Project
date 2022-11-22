package com.example.studentmap.service.impl;

import com.example.studentmap.model.Location;
import com.example.studentmap.repository.LocationRepository;
import com.example.studentmap.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations(){
        return locationRepository.getAllLocations();
    }

    @Override
    public Location getLocationByName(String id){
        return locationRepository.getLocationByName(id);
    }

    @Override
    public List<Location> getLocationsByType(String type){
        return locationRepository.getLocationsByType(type);
    }
}
