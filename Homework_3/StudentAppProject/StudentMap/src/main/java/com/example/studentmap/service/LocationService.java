package com.example.studentmap.service;

import com.example.studentmap.model.Location;

import java.util.List;

public interface LocationService{
    List<Location> getAllLocations();
    List<Location> getLocationByNameOrAddress(String id);
    List<Location> getLocationsByType(String type);
    void populateDataBase(List<Location> locations);
    Location getLocationById(Long id);
    Location createOrUpdate(String x, String y, String type, String name, String address, String phone, String website, String openingHours,Long id);
    void deleteById(Long id);
    public double calculateAverageGrade(Long id, int grade);

    Location save(Location location);
}
