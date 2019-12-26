package com.emmardo.p5.firestation;

import com.emmardo.p5.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class FireStationController {

    Logger logger = LoggerFactory.getLogger(FireStationController.class);

    @Autowired
    private FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {

        this.fireStationService = fireStationService;
    }

    @GetMapping("/FireStations")
    public List<FireStation> getAllFireStations() {

        logger.info("getAllFireStations() called");
        return fireStationService.getAllFireStations();
    }

    @GetMapping("/FireStation")
    public FireStation getFireStation(@RequestParam String address) {

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            logger.info("getFireStation() called");
        }else{

            logger.error("getFireStation() ERROR");
        }

        return fireStationService.getFireStation(address);
    }

    @PostMapping("/FireStation")
    public void createFireStation(@RequestBody FireStation fireStation) {

        if(fireStationService.getAllFireStations().stream().noneMatch(fs -> fs.getAddress().equals(fireStation.getAddress()))) {

            logger.info("createFireStation() called");
        }else{

            logger.error("createFireStation() ERROR");
        }

        fireStationService.createFireStation(fireStation);
    }

    @PutMapping("/FireStation")
    public void updateFireStationNumber(@RequestBody FireStation fireStation) {

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(fireStation.getAddress()))) {

            logger.info("updateFireStationNumber() called");
        }else{

            logger.error("updateFireStationNumber() ERROR");
        }

        fireStationService.updateStation(fireStation);
    }

    @DeleteMapping("/FireStation")
    public void deleteFireStation(@RequestParam String address) {

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            logger.info("deleteFireStation() called");
        }else{

            logger.error("deleteFireStation() ERROR");
        }

        fireStationService.deleteStation(address);
    }

    @GetMapping("/firestation")
    public List<Person> adultsAndChildrenServicedByFireStation(@RequestParam String stationNumber) {

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getStation().equals(stationNumber))) {

            logger.info("/firestation called");
        }else{

            logger.error("/firestation ERROR");
        }

        return fireStationService.getAdultsAndChildrenServicedByFireStation(stationNumber);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhonesByStation(@RequestParam String firestation) {

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getStation().equals(firestation))) {

            logger.info("/phoneAlert called");
        }else{

            logger.error("/phoneAlert ERROR");
        }

        return fireStationService.getPhonesByStation(firestation);
    }

    @GetMapping("/fire")
    public HashMap<String, List<Person>> getFireStationAndPersonsByAddress(@RequestParam String address) {

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            logger.info("/fire called");
        }else{

            logger.error("/fire ERROR");
        }

        return fireStationService.getFireStationAndPersonsByAddress(address);
    }

    //CHECK!!!
    @GetMapping("/flood/stations")
    public HashMap<String, List<Person>> getHouseholdsCoveredByStations(@RequestParam List<String> stations) {


        if(stations.stream().anyMatch(sn -> sn.equals(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getStation().equals(sn))))) {

            logger.info("/flood/stations called");
        }else{

            logger.error("/flood/stations ERROR");
        }

        return fireStationService.getHouseholdsCoveredByStations(stations);
    }

    @GetMapping("/personInfo")
    public Person getPersonsInformation(@RequestParam String firstName, @RequestParam String lastName) {

        if(!fireStationService.getPersonsInformation(firstName, lastName).getFirstName().isEmpty()) {

            logger.info("/personInfo called");
        }else{

            logger.error("/personInfo ERROR");
        }

        return fireStationService.getPersonsInformation(firstName, lastName);
    }

    @GetMapping("/childAlert")
    public List<Person> getChildrenLivingAtAddressAsWellAsAnyAdult(@RequestParam String address) {

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            logger.info("/childAlert called");
        }else{

            logger.error("/childAlert ERROR");
        }

        return fireStationService.getChildrenLivingAtAddressAsWellAsAnyAdult(address);
    }
}
