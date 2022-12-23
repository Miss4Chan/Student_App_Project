package com.example.studentmap.repository;

import com.example.studentmap.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String text1, String text2);
    List<Location> findAllByType(String type);
    //TODO add repository methods as needed
}
