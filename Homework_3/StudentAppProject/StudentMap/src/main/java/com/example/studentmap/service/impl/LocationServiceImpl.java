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
        return locationRepository.findAll();
    }

    @Override
    public List<Location> getLocationByName(String name){
        return locationRepository.findAllByNameContainingIgnoreCase(name);
    }
    @Override
    public double calculateAverageGrade(Long id, int grade) {
        Location location = locationRepository.findById(id).get();
        int graders = location.getGraders();
        double prevAvg = location.getAverageGrade();
        double averageGrade = (location.getAverageGrade()*location.getGraders() + grade)/ (location.getGraders()+1);
        location.setAverageGrade(averageGrade);
        location.setGraders(location.getGraders()+1);
        System.out.println(location.getGraders());
        System.out.println(location.getAverageGrade());
        locationRepository.save(location);
        return averageGrade;
    }
    @Override
    public List<Location> getLocationsByType(String type){
        return locationRepository.findAllByType(type);
    }

    @Override
    public void populateDataBase(List<Location> locations) {
        locationRepository.saveAll(locations);
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow(); // TODO: 23.11.2022 put exception 
    }

    @Override
    public Location createOrUpdate(float x, float y, String type, String name, String address, String phone, String website, String openingHours) {
        return locationRepository.save(new Location(x,y,type,name,address,phone,website,openingHours));
    }

    @Override
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
