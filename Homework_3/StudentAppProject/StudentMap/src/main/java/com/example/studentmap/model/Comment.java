package com.example.studentmap.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    // TODO: 23.11.2022 Have user for database foreign key implementation
    private String comment;
}
