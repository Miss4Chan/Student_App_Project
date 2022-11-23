package com.example.studentmap.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Rating {
    // TODO: 23.11.2022 Have user/location for database foreign key implementation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rating;
    @OneToOne
    private StudentMapUser user;
    @OneToOne
    private Location location;
}
