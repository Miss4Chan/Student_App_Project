package finki.ukim.mk.studentmap.model;


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
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(mappedBy = "favourites")
    @JsonManagedReference
    private List<Location> locationList;

    public Favourites(User user) {
        this.user = user;
        this.locationList= new ArrayList<>();
    }
}
