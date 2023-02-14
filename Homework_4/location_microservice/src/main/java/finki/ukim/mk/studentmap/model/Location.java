package finki.ukim.mk.studentmap.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Location {
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
    private double averageGrade;
    private int graders;
    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToMany
    @JsonBackReference
    private List<Favourites> favourites;

    public Location(float x, float y, String type, String name, String address, String phone, String website, String openingHours) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.openingHours = openingHours;
        comments = new ArrayList<>();
        favourites = new ArrayList<>();
        averageGrade = 0;
        graders = 0;
    }

}

