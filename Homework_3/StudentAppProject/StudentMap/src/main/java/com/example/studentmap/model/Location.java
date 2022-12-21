package com.example.studentmap.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
//added lombok notation for easy writing od classes
public class Location{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float x;
    private float y;
    private String type;
    private String name;
    private String address;
    private String phone;
    private String website;
    private String openingHours;
    private double avgGrade; //not in constructor
    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comment> comments;

    public Location(float x, float y, String type, String name, String address, String phone, String website, String openingHours) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.openingHours = openingHours;
    }

}

