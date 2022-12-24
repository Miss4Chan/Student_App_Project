package com.example.studentmap.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Location location;

    public Comment(String comment, User user, Location location){
        this.comment = comment;
        this.user = user;
        this.location = location;
    }
}
