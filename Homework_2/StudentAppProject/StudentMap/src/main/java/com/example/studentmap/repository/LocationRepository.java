package com.example.studentmap.repository;

import com.example.studentmap.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.studentmap.database.DataHolder.locations;

@Repository
public class LocationRepository{
    public List<Location> getAllLocations(){
        return locations;
    }

    public Location getLocationByName(String name){
        return locations.stream().filter(l->l.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Location> getLocationsByType(String type){
        return locations.stream().filter(l->l.getType().equals(type)).collect(Collectors.toList());
    }

    //TODO add repository methods as needed
}
