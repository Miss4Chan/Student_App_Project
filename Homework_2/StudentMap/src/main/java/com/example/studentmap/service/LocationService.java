package com.example.studentmap.service;

import com.example.studentmap.model.Location;

import java.util.List;

public interface LocationService{
    List<Location> getAllLocations();
    Location getLocationByName(String id);
    List<Location> getLocationsByType(String type);
}
