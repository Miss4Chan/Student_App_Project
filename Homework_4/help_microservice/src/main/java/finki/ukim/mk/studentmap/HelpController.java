package finki.ukim.mk.studentmap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HelpController {

    @GetMapping("/help")
    public String getHelpPage(){return "helpPage";}

    @GetMapping("/locations")
    public RedirectView getLocations(){
        return new RedirectView("http://localhost:9093/locations");}
}
