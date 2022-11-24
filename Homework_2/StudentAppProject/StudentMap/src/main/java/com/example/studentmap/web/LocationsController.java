package com.example.studentmap.web;

import com.example.studentmap.model.Location;
import com.example.studentmap.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/locations")
public class LocationsController {
    private final LocationService locationService;

    public LocationsController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/all")
    public String getLocationsPage(@RequestParam(required = false) String error, Model model) {
        List<Location> locations = this.locationService.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("error", error);
        return "listLocations";
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) throws JsonProcessingException {
        //samo universities
        List<Location> locations = this.locationService.getLocationsByType("university");
        //model.addAttribute("locations", locations);
        model.addAttribute("error", error);
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(locations);
        model.addAttribute("locations", arrayToJson);
        return "mapa";
    }

    @GetMapping("/{name}")
    public String getDetailsPage(@PathVariable String name, Model model) {
        Location location = this.locationService.getLocationByName(name).get(0);
        if (location == null)
            return "redirect:/locations?error=Location not found";
        model.addAttribute("location", location);
        return "details";
    }

    @GetMapping("/create-form")
    public String addLocationPage(Model model) {
        List<Location> locations = this.locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "add-location";
    }


    @PostMapping("/create")
    public String createLocation(@RequestParam float x,
                                 @RequestParam float y,
                                 @RequestParam String type,
                                 @RequestParam String name,
                                 @RequestParam String address,
                                 @RequestParam String phone,
                                 @RequestParam String website,
                                 @RequestParam String openingHours) throws InterruptedException, ExecutionException {
        locationService.createOrUpdate(x, y, type, name, address, phone, website, openingHours);
        return "redirect:/locations";
    }


    @GetMapping("/get/{id}")
    public Location getShopById(@PathVariable Long id) throws InterruptedException, ExecutionException {
        return locationService.getLocationById(id);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteShop(@PathVariable Long id) {
        locationService.deleteById(id);
        return "redirect:/locations";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
            Location location = this.locationService.getLocationById(id);
            model.addAttribute("location", location);
            return "add-location";
    }

    // TODO: 24-Nov-22 post method na locations za search

    // TODO: 24-Nov-22 post method za filter by type

}
