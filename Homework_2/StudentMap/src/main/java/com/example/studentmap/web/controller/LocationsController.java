package com.example.studentmap.web.controller;

import com.example.studentmap.model.Location;
import com.example.studentmap.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationsController{
    private final LocationService locationService;

    public LocationsController(LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping
    public String getLocationsPage(@RequestParam(required = false) String error, Model model){
        List<Location> locations = this.locationService.getAllLocations();
        model.addAttribute("locations",locations);
        model.addAttribute("error",error);
        return "listLocations";
    }

    @GetMapping("/{name}") //ke ni treba da se generira nekakvo id za
    // opcija 1: moze da se generira vo FileFixer i da se stavi vo locations.csv
    // opcija 2: moze da se generira vo DataHolder i da go ima samo vo modelot - jas bi vaka
    public String getDetailsPage(@PathVariable String name, Model model){
        Location location = this.locationService.getLocationByName(name);
        if(location == null)
            return "redirect:/locations?error=Location not found";
        model.addAttribute("location",location);
        //details ne e implementirano <3
        return "details";
    }
}
