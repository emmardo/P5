package com.emmardo.p5.firestation;

import com.emmardo.p5.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

        logger.info("GET /FireStations request made");
        return fireStationService.getAllFireStations();
    }

    @GetMapping("/FireStation")
    public FireStation getFireStation(@RequestParam String address) {

        logger.info("GET /FireStation request made with the parameter " + address);

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            logger.info("GET /FireStation request SUCCESSFUL");
        }else{

            logger.error("GET /FireStation request ERROR");
        }

        return fireStationService.getFireStation(address);
    }

    @PostMapping("/FireStation")
    public void createFireStation(@RequestBody FireStation fireStation) {

        logger.info("POST /FireStation request made with the a Fire Station of details " + fireStation.getAddress() + " and " + fireStation.getStation());

        if(fireStationService.getAllFireStations().stream().noneMatch(fs -> fs.getAddress().equals(fireStation.getAddress()))) {

            logger.info("POST /FireStation request SUCCESSFUL");
        }else{

            logger.error("POST /FireStation request ERROR");
        }

        fireStationService.createFireStation(fireStation);
    }

    @PutMapping("/FireStation")
    public void updateFireStationNumber(@RequestBody FireStation fireStation) {

        logger.info("PUT /FireStation request made with the a Fire Station of details " + fireStation.getAddress() + " and " + fireStation.getStation());

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(fireStation.getAddress()))) {

            logger.info("PUT /FireStation request SUCCESSFUL");
        }else{

            logger.error("PUT /FireStation request ERROR");
        }

        fireStationService.updateStation(fireStation);
    }

    @DeleteMapping("/FireStation")
    public void deleteFireStation(@RequestParam String address) {

        logger.info("DELETE /FireStation request made with the parameter " + address);

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            logger.info("DELETE /FireStation request SUCCESSFUL");
        }else{

            logger.error("DELETE /FireStation request ERROR");
        }

        fireStationService.deleteStation(address);
    }

    @GetMapping("/firestation")
    public List<Person> adultsAndChildrenServicedByFireStation(@RequestParam String stationNumber) {

        logger.info("GET /firestation request made with the parameter " + stationNumber);

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getStation().equals(stationNumber))) {

            logger.info("GET /firestation request SUCCESSFUL");
        }else{

            logger.error("GET /firestation request ERROR");
        }

        return fireStationService.getAdultsAndChildrenServicedByFireStation(stationNumber);
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhonesByStation(@RequestParam String firestation) {

        logger.info("GET /phoneAlert request made with the parameter " + firestation);

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getStation().equals(firestation))) {

            logger.info("GET /phoneAlert request SUCCESSFUL");
        }else{

            logger.error("GET /phoneAlert request ERROR");
        }

        return fireStationService.getPhonesByStation(firestation);
    }

    @GetMapping("/fire")
    public HashMap<String, List<Person>> getFireStationAndPersonsByAddress(@RequestParam String address) {

        logger.info("GET /fire request made with the parameter " + address);

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            logger.info("GET /fire request SUCCESSFUL");
        }else{

            logger.error("GET /fire request ERROR");
        }

        return fireStationService.getFireStationAndPersonsByAddress(address);
    }

    @GetMapping("/flood/stations")
    public HashMap<String, List<Person>> getHouseholdsCoveredByStations(@RequestParam List<String> stations) {

        logger.info("GET /flood/stations request made with the parameters " + Arrays.toString(stations.toArray()));

        if(stations.stream().anyMatch(sn -> sn.equals(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getStation().equals(sn))))) {

            logger.info("GET /flood/stations request SUCCESSFUL");
        }else{

            logger.error("GET /flood/stations request ERROR");
        }

        return fireStationService.getHouseholdsCoveredByStations(stations);
    }

    @GetMapping("/personInfo")
    public Person getPersonsInformation(@RequestParam String firstName, @RequestParam String lastName) {

        logger.info("GET /personInfo request made with the parameters " + firstName + " and " + lastName);

        if(!fireStationService.getPersonsInformation(firstName, lastName).getFirstName().isEmpty()) {

            logger.info("GET /personInfo request SUCCESSFUL");
        }else{

            logger.error("GET /personInfo request ERROR");
        }

        return fireStationService.getPersonsInformation(firstName, lastName);
    }

    @GetMapping("/childAlert")
    public List<Person> getChildrenLivingAtAddressAsWellAsAnyAdult(@RequestParam String address) {

        logger.info("GET /childAlert request made with the parameter " + address);

        if(fireStationService.getAllFireStations().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            logger.info("GET /childAlert request SUCCESSFUL");
        }else{

            logger.error("GET /childAlert request ERROR");
        }

        return fireStationService.getChildrenLivingAtAddressAsWellAsAnyAdult(address);
    }
}
