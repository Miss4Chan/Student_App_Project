package finki.ukim.mk.studentmap.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import finki.ukim.mk.studentmap.model.Comment;
import finki.ukim.mk.studentmap.model.Location;
import finki.ukim.mk.studentmap.model.User;
import finki.ukim.mk.studentmap.repository.UserRepository;
import finki.ukim.mk.studentmap.service.CommentService;
import finki.ukim.mk.studentmap.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    private final CommentService commentService;
    private final LocationService locationService;
    private final UserRepository userRepository;

    public CommentsController(CommentService commentService, LocationService locationService,
                              UserRepository userRepository) {
        this.commentService = commentService;
        this.locationService = locationService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String getAllComments(@RequestParam(required = false) String error, Model model) {
        List<Comment> comments = this.commentService.getAll();
        model.addAttribute("comments", comments);
        model.addAttribute("error", error);
        return "allComments";
    }

    @PostMapping("/{id}")
    @ResponseBody
    public List<Comment> getAllCommentsForLocation(@PathVariable Long id, @RequestParam(required = false) String error,
                                                   Model model) throws JsonProcessingException {
        List<Comment> comments = this.commentService.getAllCommentsByLocationId(id);
        model.addAttribute("error", error);
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = objectMapper.writeValueAsString(comments);
        model.addAttribute("comments", arrayToJson);
        return comments;
    }

    @GetMapping("/add-comment/{id}")
    public String addCommentPage(@PathVariable Long id,
                                 @RequestParam(required = false) String error, Model model) {
        Location location = this.locationService.getLocationById(id);
        model.addAttribute("error", error);
        model.addAttribute("location", location);
        return "add-comment";
    }

    @PostMapping("/add-comment/{id}")
    public String createComment(@PathVariable String id, @RequestParam String comment,
                                @RequestParam String name, Model model, @RequestParam(required = false) String error) {
        Location location = this.locationService.getLocationById(Long.valueOf(id));
        model.addAttribute("error", error);
        model.addAttribute("location", location);
        User user = userRepository.findByName(name).get();
        this.commentService.createComment(comment, user, location);
        return "redirect:/locations";
    }

    @PostMapping("/delete-comment/{id}")
    public String deleteCommentTestingTesting(@PathVariable String id) {
        this.commentService.deleteById(Long.valueOf(id));
        return "redirect:/locations";
    }
}