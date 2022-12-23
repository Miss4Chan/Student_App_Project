package com.example.studentmap.web;

import com.example.studentmap.model.Comment;
import com.example.studentmap.model.Location;
import com.example.studentmap.model.User;
import com.example.studentmap.repository.UserRepository;
import com.example.studentmap.service.CommentService;
import com.example.studentmap.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import org.apache.tomcat.util.json.JSONParser;
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

    //    @GetMapping("/{id}")
//    public String getAllCommentsForLocationPage(@PathVariable Long id,
//                                            @RequestParam(required = false) String error, Model model) {
//        List<Comment> comments = this.commentService.getAllCommentsByLocation_Id(id);
//        model.addAttribute("comments", comments);
//        model.addAttribute("error", error);
//        return "allComments";
//    }

    @PostMapping("/{id}")
    @ResponseBody
    public List<Comment> getAllCommentsForLocation(@PathVariable Long id, @RequestParam(required = false) String error,
                                            Model model) throws JsonProcessingException{
        List<Comment> comments = this.commentService.getAllCommentsByLocationId(id);
        model.addAttribute("error", error);
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(comments);
        model.addAttribute("comments", arrayToJson);
        return comments;
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
                                @RequestParam String name, Model model, @RequestParam(required = false) String error){
        Location location = this.locationService.getLocationById(Long.valueOf(id));
        model.addAttribute("error", error);
        model.addAttribute("location", location);
        User user = userRepository.findByName(name).get();
        this.commentService.createComment(comment, user, location);
        return "redirect:/locations";
    }
}