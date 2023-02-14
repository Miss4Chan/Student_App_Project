package finki.ukim.mk.studentmap.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import finki.ukim.mk.studentmap.model.Favourites;
import finki.ukim.mk.studentmap.model.Location;
import finki.ukim.mk.studentmap.model.User;
import finki.ukim.mk.studentmap.service.FavouritesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavouritesService favouritesService;

    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }



    @PostMapping("/add-location-to-faves/{id}")
    public String addLocationToFaves(@PathVariable Long id,
                                     Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.favouritesService.addLocationToFaves(user.getUsername(), id);
            return "redirect:/locations";
        } catch (RuntimeException exception) {
            return "redirect:/locations?error=" + exception.getMessage();
        }
    }

    @PostMapping("/get-favourites")
    @ResponseBody
    public String getFavourites(HttpServletRequest req) throws JsonProcessingException {
        String username = req.getRemoteUser();
        Favourites favourite = this.favouritesService.getFave(username).get();
        List<Location> locations = this.favouritesService.listAllFaves(favourite.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = objectMapper.writeValueAsString(locations);
        return arrayToJson;
    }
}
