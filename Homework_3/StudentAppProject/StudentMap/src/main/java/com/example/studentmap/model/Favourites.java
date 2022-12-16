package com.example.studentmap.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Location> locationList;

    public Favourites(User user) {
        this.user = user;
        this.dateCreated=LocalDateTime.now();
        this.locationList= new ArrayList<>();
    }
}
