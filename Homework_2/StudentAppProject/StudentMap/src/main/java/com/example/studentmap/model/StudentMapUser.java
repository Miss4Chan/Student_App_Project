package com.example.studentmap.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "StudentMapUser")
public class StudentMapUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    // TODO: 23.11.2022 should be enumerated 
    private Role role;
    @OneToMany(fetch = FetchType.EAGER) //Takes all locations with all attributes
    private List<Location> favouriteLocation;
}
