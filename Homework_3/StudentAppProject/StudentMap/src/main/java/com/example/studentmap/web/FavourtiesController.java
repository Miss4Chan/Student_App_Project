package com.example.studentmap.web;

import com.example.studentmap.model.Favourites;
import com.example.studentmap.model.User;
import com.example.studentmap.service.FavouritesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/favourites")
public class FavourtiesController {

    private final FavouritesService favouritesService;

    public FavourtiesController(FavouritesService favouritesService){
        this.favouritesService=favouritesService;
    }

    @GetMapping
    public String getFavouritesPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        if(this.favouritesService.getFave(username).isPresent()) {
            Favourites favourites = this.favouritesService.getFave(username).get();
            model.addAttribute("locations", this.favouritesService.listAllFaves(favourites.getId()));
            return "favourites";
        }
        model.addAttribute("locations",null);
        return "favourites"; //nema model attr zatoa shto e prazna - display prazna lista or whatever
    }
    @PostMapping("/add-location-to-faves/{id}")
    public String addLocationToFaves(@PathVariable Long id,
                                     Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.favouritesService.addLocationToFaves(user.getUsername(), id);
            return "redirect:/favourites";
        } catch (RuntimeException exception) {
            return "redirect:/locations?error=" + exception.getMessage();
        }
    }
}
