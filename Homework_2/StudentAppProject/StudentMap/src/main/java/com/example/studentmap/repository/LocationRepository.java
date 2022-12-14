package com.example.studentmap.repository;

import com.example.studentmap.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.studentmap.database.DataHolder.locations;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findAllByNameContainingIgnoreCase(String name);
    List<Location> findAllByType(String type);

    //TODO add repository methods as needed
}
