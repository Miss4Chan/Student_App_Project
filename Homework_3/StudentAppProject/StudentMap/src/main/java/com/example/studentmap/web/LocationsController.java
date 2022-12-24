package com.example.studentmap.web;

import com.example.studentmap.model.Comment;
import com.example.studentmap.model.Favourites;
import com.example.studentmap.model.Location;
import com.example.studentmap.service.CommentService;
import com.example.studentmap.service.FavouritesService;
import com.example.studentmap.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/locations")
public class LocationsController {
    private final LocationService locationService;
    private final CommentService commentService;
    private final FavouritesService favouritesService;

    public LocationsController(LocationService locationService, CommentService commentService, FavouritesService favouritesService) {
        this.locationService = locationService;
        this.commentService = commentService;
        this.favouritesService = favouritesService;
    }

    @GetMapping("/all")
    public String getLocationsPage(@RequestParam(required = false) String error, Model model) {
        List<Location> locations = this.locationService.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("error", error);
        return "listLocations";
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String loginError,
                              @RequestParam(required = false) String registerError,
                              Model model) throws JsonProcessingException {
        //samo universities
        List<Location> locations = this.locationService.getLocationsByType("university");

        if(loginError != null) {
            model.addAttribute("hasLoginError", true);
            model.addAttribute("loginError", loginError);
        }
        if(registerError != null) {
            model.addAttribute("hasRegisterError", true);
            model.addAttribute("registerError", registerError);
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
        List<Location> locations = this.locationService.getLocationByNameOrAddress(text);
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

    @PostMapping("/get-comments/{id}")
    @ResponseBody
    public String getComments(@PathVariable Long id) throws JsonProcessingException{
        Location location = locationService.getLocationById(id);
        List<Comment> comments = location.getComments();
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(comments);
        return arrayToJson;
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
                                 @RequestParam String openingHours,
                                 @RequestParam(required = false) Long id) throws InterruptedException, ExecutionException {
        this.locationService.createOrUpdate(x,y, type, name, address, phone, website, openingHours, id);
        return "redirect:/locations";
    }


    @GetMapping("/get/{id}")
    public Location getLocationById(@PathVariable Long id) throws InterruptedException, ExecutionException {
        return locationService.getLocationById(id);
    }


    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        List<Comment> comments = commentService.getAllCommentsByLocationId(id);
        comments.stream().forEach(c -> commentService.deleteById(c.getId()));
//        List<Favourites> favourites = favouritesService.getAllFavouritesByLocationId(id);
//        favourites.stream().forEach(f -> favouritesService.deleteById(f.getId())); NE RABOTI
        locationService.deleteById(id);
        return "redirect:/locations";
    }

    @GetMapping("/edit-form/{id}")
    public String editLocationPage(@PathVariable Long id, Model model) {
        Location location = this.locationService.getLocationById(id);
        model.addAttribute("location", location);
        return "add-location";
    }

    @GetMapping("/help")
    public String getHelpPage(){
        return "helpPage";
    }

}
