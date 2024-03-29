package finki.ukim.mk.studentmap.service.impl;

import finki.ukim.mk.studentmap.model.Location;
import finki.ukim.mk.studentmap.model.exceptions.InvalidArgumentsException;
import finki.ukim.mk.studentmap.repository.LocationRepository;
import finki.ukim.mk.studentmap.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;


    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> getLocationByNameOrAddress(String text) {
        return locationRepository.findAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(text, text);
    }

    @Override
    public double calculateAverageGrade(Long id, int grade) {
        Location location = locationRepository.findById(id).get();
<<<<<<< HEAD
        int graders = location.getGraders();
        double prevAvg = location.getAverageGrade();
        double averageGrade = (location.getAverageGrade() * location.getGraders() + grade) / (location.getGraders() + 1);
        String gradeString = String.format("%.2f", averageGrade);
        location.setAverageGrade(averageGrade);
        location.setGraders(location.getGraders() + 1);
=======
        double averageGrade = (location.getAverageGrade()*location.getGraders() + grade)/ (location.getGraders()+1);
        location.setAverageGrade(averageGrade);
        location.setGraders(location.getGraders()+1);
>>>>>>> origin/main
        locationRepository.save(location);
        return averageGrade;
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getLocationsByType(String type) {
        return locationRepository.findAllByType(type);
    }

    @Override
    public void populateDataBase(List<Location> locations) {
        locationRepository.saveAll(locations);
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow();
    }

    @Override
    public Location createOrUpdate(String x, String y, String type, String name, String address, String phone, String website, String openingHours, Long id) {
        Location location = null;
        try {
            if (id == null) {
                location = new Location(Float.parseFloat(x), Float.parseFloat(y), type, name, address, phone, website, openingHours);
            } else {
                Float.parseFloat(x);
                Float.parseFloat(y);
                location = locationRepository.findById(id).get();
                location.setX(Float.parseFloat(x));
                location.setY(Float.parseFloat(y));
                location.setName(name);
                location.setType(type);
                location.setAddress(address);
                location.setPhone(phone);
                location.setWebsite(website);
                location.setOpeningHours(openingHours);
            }
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException();
        }
        return locationRepository.save(location);
    }

    @Override
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
