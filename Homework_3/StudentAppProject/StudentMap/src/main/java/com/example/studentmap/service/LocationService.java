package com.example.studentmap.service;

import com.example.studentmap.model.Location;

import java.util.List;

public interface LocationService{
    List<Location> getAllLocations();
    List<Location> getLocationByName(String id);
    List<Location> getLocationsByType(String type);
    void populateDataBase(List<Location> locations);
    Location getLocationById(Long id);
    Location createOrUpdate(float x, float y, String type, String name, String address, String phone, String website, String openingHours);
    void deleteById(Long id);
}
