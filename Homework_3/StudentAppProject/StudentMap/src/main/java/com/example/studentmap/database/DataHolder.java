//package com.example.studentmap.database;
//
//import com.example.studentmap.model.Location;
//import com.example.studentmap.service.LocationService;
//import lombok.AllArgsConstructor;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@AllArgsConstructor
//public class DataHolder{
//    public static List<Location> locations;
//    private final LocationService locationService;
//
//    @PostConstruct
//    public void init() throws IOException{
//        locations = new ArrayList<>();
//        ClassPathResource r = new ClassPathResource("csvFiles/locations.csv");
//
//        try{
//            InputStream is = r.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String l = br.readLine(); //da gi nema naslovite na kategoriite
//            locations = br.lines().map(line->{
//                String[] parts = line.split(",",-1);
//                return new Location(Float.parseFloat(parts[0]),Float.parseFloat(parts[1]),
//                        parts[2],parts[3],parts[4],parts[5],parts[6],parts[7]);
//            }).collect(Collectors.toList());
//            locations.removeIf(x->x.getName().isBlank());//ako nema ime brishi
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//        locationService.populateDataBase(locations);
//    }
//}
