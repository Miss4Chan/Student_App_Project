package finki.ukim.mk.studentmap.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import finki.ukim.mk.studentmap.model.Comment;
import finki.ukim.mk.studentmap.model.Location;
import finki.ukim.mk.studentmap.model.exceptions.InvalidArgumentsException;
import finki.ukim.mk.studentmap.service.CommentService;
import finki.ukim.mk.studentmap.service.FavouritesService;
import finki.ukim.mk.studentmap.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

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
        List<Location> locations = this.locationService.getLocationsByType("university");

        if(loginError != null) {
            model.addAttribute("hasLoginError", true);
            model.addAttribute("loginError", loginError);
        }
        if(registerError != null) {
            model.addAttribute("hasRegisterError", true);
            model.addAttribute("registerError", registerError);
        }
        String arrayToJson = arrayToJson(locations);
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
        String arrayToJson = arrayToJson(locations);
        model.addAttribute("locations", arrayToJson);
        return "mapa";
    }

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
        String result = String.format("%.2f",averageGrade);
        return result;
    }

    @PostMapping("/deleteRating/{id}")
    @ResponseBody
    public String deleteGrade(@PathVariable Long id){
        Location location = locationService.getLocationById(id);
        location.setGraders(0);
        location.setAverageGrade(0.0);
        locationService.save(location);
        return String.valueOf(0);
    }

    @PostMapping("/get-comments/{id}")
    @ResponseBody
    public String getComments(@PathVariable Long id) throws JsonProcessingException{
        Location location = locationService.getLocationById(id);
        List<Comment> comments = location.getComments();
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = objectMapper.writeValueAsString(comments);
        return arrayToJson;
    }

    @GetMapping("/select/{value}")
    public String getCategory(@PathVariable String value, Model model) throws JsonProcessingException {
        List<Location> locations = this.locationService.getLocationsByType(value);
        String arrayToJson = arrayToJson(locations);
        model.addAttribute("locations", arrayToJson);
        model.addAttribute("selectedValue",value);
        return "mapa";
    }


    @PostMapping("/create")
    public String createLocation(@RequestParam String x,
                                 @RequestParam String y,
                                 @RequestParam String type,
                                 @RequestParam String name,
                                 @RequestParam String address,
                                 @RequestParam String phone,
                                 @RequestParam String website,
                                 @RequestParam String openingHours,
                                 @RequestParam(required = false) Long id,Model model) throws InterruptedException, ExecutionException {
        try {

            this.locationService.createOrUpdate(x, y, type, name, address, phone, website, openingHours, id);
        }
        catch (InvalidArgumentsException exception) {
            model.addAttribute("hasLoginError", true);
            model.addAttribute("loginError", exception.getMessage());
            return "mapa";
        }
        return "redirect:/locations";
    }


    @GetMapping("/get/{id}")
    public Location getLocationById(@PathVariable Long id) throws InterruptedException, ExecutionException {
        return locationService.getLocationById(id);
    }


    @GetMapping("/delete")
    public String deleteLocation(@RequestParam Long id) {
        List<Comment> comments = commentService.getAllCommentsByLocationId(id);
        comments.stream().forEach(c -> commentService.deleteById(c.getId()));
        Location location = locationService.getLocationById(id);
        if(location.getFavourites().size()>0){
            location.getFavourites().forEach(f->f.getLocationList().remove(location));
            favouritesService.saveAll(location.getFavourites());
        }
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
    public RedirectView getHelpPage(){
        return new RedirectView("http://localhost:9092/help");}

    private String arrayToJson(List<Location> array) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(array);
        return arrayToJson;
    }
}
