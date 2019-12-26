package com.emmardo.p5.firestation;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStationRepository {

    private List<FireStation> fireStations;

    public FireStationRepository() {

        fireStations = new ArrayList<>();
    }

    public List<FireStation> findAll() {

        return fireStations.stream().collect(Collectors.toList());
    }

    public FireStation getFireStation(String address){

        FireStation fireStation;

        if(fireStations.stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            fireStation = fireStations.stream().filter(fs -> fs.getAddress().equals(address)).findFirst().get();
        }else{

            fireStation = null;
        }

        return fireStation;
    }

    public void createFireStation(FireStation fireStation) {

        if(fireStations.stream().noneMatch(fs -> fs.getAddress().equals(fireStation.getAddress()))) {

            fireStations.add(fireStation);
        }
    }

    public void deleteFireStation(String address) {

        FireStation fireStation = getFireStation(address);

        if (fireStation != null) {

            fireStations.remove(fireStation);
        }
    }
}
