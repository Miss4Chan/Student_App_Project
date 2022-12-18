package com.example.studentmap.web;

import com.example.studentmap.model.Comment;
import com.example.studentmap.model.Location;
import com.example.studentmap.model.User;
import com.example.studentmap.repository.UserRepository;
import com.example.studentmap.service.CommentService;
import com.example.studentmap.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentsController{
    private final CommentService commentService;
    private final LocationService locationService;
    private final UserRepository userRepository;

    public CommentsController(CommentService commentService, LocationService locationService,
                              UserRepository userRepository){
        this.commentService = commentService;
        this.locationService = locationService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String getAllComments(@RequestParam(required = false) String error, Model model){
        List<Comment> comments = this.commentService.getAll();
        model.addAttribute("comments", comments);
        model.addAttribute("error", error);
        return "allComments";
    }

    @GetMapping("/{id}")
    public String getAllCommentsForLocation(@PathVariable Long id,
                                            @RequestParam(required = false) String error, Model model) {
        List<Comment> comments = this.commentService.getAllCommentsByLocation_Id(id);
        model.addAttribute("comments", comments);
        model.addAttribute("error", error);
        return "allComments";
    }

    @GetMapping("/add-comment/{id}")
    public String addCommentPage(@PathVariable Long id,
                                 @RequestParam(required = false) String error, Model model){
        Location location = this.locationService.getLocationById(id);
        model.addAttribute("error", error);
        model.addAttribute("location", location);
        return "add-comment";
    }

    @PostMapping("/add-comment/{id}")
    public String createComment(@PathVariable String id, @RequestParam String comment,
                                @RequestParam String name){
        Location location = this.locationService.getLocationById(Long.valueOf(id));
        User user = userRepository.findByName(name).get();
        this.commentService.createComment(comment, user, location);
        return "redirect:/comments";
    }
}
