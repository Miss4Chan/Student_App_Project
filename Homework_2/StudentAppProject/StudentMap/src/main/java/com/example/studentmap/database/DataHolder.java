package com.example.studentmap.database;

import com.example.studentmap.model.Location;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataHolder{
    public static List<Location> locations;
    public static List<Location> locationtest;
//zdr hihi
    @PostConstruct
    public void init() throws IOException{
        locations = new ArrayList<>();
        File f = new File("src/main/java/com/example/studentmap/database/locations.csv");

        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            String l = br.readLine(); //da gi nema naslovite na kategoriite
            locations = br.lines().map(line->{
                String[] parts = line.split(",",-1);
                return new Location(Float.parseFloat(parts[0]),Float.parseFloat(parts[1]),
                        parts[2],parts[3],parts[4],parts[5],parts[6],parts[7]);
            }).collect(Collectors.toList());
            locations.removeIf(x->x.getName().isBlank());//ako nema ime brishi
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
