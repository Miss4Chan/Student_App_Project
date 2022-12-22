package com.example.studentmap.web;

import com.example.studentmap.model.Location;
import com.example.studentmap.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
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
        if(error != null) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(locations);
        model.addAttribute("locations", arrayToJson);
        return "mapa";
    }

    @PostMapping("/search")
    public String returnSearch(@RequestParam(required = false) String error,
                               @RequestParam(required=false) String text,
                               Model model) throws JsonProcessingException {
        if(Objects.equals(text, "")){
            return "redirect:/locations";
        }
        List<Location> locations = this.locationService.getLocationByName(text);
        model.addAttribute("error", error);
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(locations);
        model.addAttribute("locations", arrayToJson);
        return "mapa";
    }
//TODO da se popravi: java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
//    @GetMapping("/{name}")
//    public String getDetailsPage(@PathVariable String name, Model model) {
//        Location location = this.locationService.getLocationByName(name).get(0);
//        if (location == null)
//            return "redirect:/locations?error=Location not found";
//        model.addAttribute("location", location);
//        return "details";
//    }

    @GetMapping("/create-form")
    public String addLocationPage(Model model) {
        List<Location> locations = this.locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "add-location";
    }
    @PostMapping("/add-grade/{id}/{grade}")
    @ResponseBody
    public String addGrade(@PathVariable int grade, @PathVariable Long id){
        double averageGrade = locationService.calculateAverageGrade(id,grade);
        String result = String.valueOf(averageGrade);
        return result;
    }

    @GetMapping("/select/{value}")
    public String getCategory(@PathVariable String value, Model model) throws JsonProcessingException {
        List<Location> locations = this.locationService.getLocationsByType(value);
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(locations);
        model.addAttribute("locations", arrayToJson);
        model.addAttribute("selectedValue",value);
        return "mapa";
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
    public Location getLocationById(@PathVariable Long id) throws InterruptedException, ExecutionException {
        return locationService.getLocationById(id);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationService.deleteById(id);
        return "redirect:/locations";
    }

    @GetMapping("/edit-form/{id}")
    public String editLocationPage(@PathVariable Long id, Model model) {
        Location location = this.locationService.getLocationById(id);
        model.addAttribute("location", location);
        return "add-location";
    }

    // TODO: 24-Nov-22 post method na locations za search

    // TODO: 24-Nov-22 post method za filter by type

}
