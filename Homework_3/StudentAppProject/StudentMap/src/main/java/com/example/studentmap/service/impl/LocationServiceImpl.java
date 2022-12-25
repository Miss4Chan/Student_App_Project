package com.example.studentmap.service.impl;

import com.example.studentmap.model.Comment;
import com.example.studentmap.model.Favourites;
import com.example.studentmap.model.Location;
import com.example.studentmap.model.exceptions.InvalidArgumentsException;
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
    public List<Location> getLocationByNameOrAddress(String text) {
        return locationRepository.findAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(text,text);
    }
    @Override
    public double calculateAverageGrade(Long id, int grade) {
        Location location = locationRepository.findById(id).get();
        int graders = location.getGraders();
        double prevAvg = location.getAverageGrade();
        double averageGrade = (location.getAverageGrade()*location.getGraders() + grade)/ (location.getGraders()+1);
        String gradeString = String.format("%.2f", averageGrade);
       // averageGrade = Double.parseDouble(gradeString);
        location.setAverageGrade(averageGrade);
        location.setGraders(location.getGraders()+1);
//        System.out.println(location.getGraders());
//        System.out.println(location.getAverageGrade());
        locationRepository.save(location);
        return averageGrade;
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
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
    public Location createOrUpdate(String x, String y, String type, String name, String address, String phone, String website, String openingHours,Long id) {
        Location location = null;
        try {
        if(id == null){
            location = new Location(Float.parseFloat(x),Float.parseFloat(y),type,name,address,phone,website,openingHours);
        }
        else{
                Float.parseFloat(x);
                Float.parseFloat(y);

                location = locationRepository.findById(id).get();
                location.setX(Float.parseFloat(x));
                location.setY(Float.parseFloat(y));
                location.setName(name);
                location.setType(type);
                location.setAddress(address);
                location.setPhone(phone);
                location.setWebsite(website);
                location.setOpeningHours(openingHours);
            }
        }
        catch (NumberFormatException e)
        {
            throw new InvalidArgumentsException();
        }
        return locationRepository.save(location);
    }

    @Override
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
