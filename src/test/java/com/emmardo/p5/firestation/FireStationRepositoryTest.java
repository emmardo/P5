package com.emmardo.p5.firestation;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FireStationRepositoryTest {

    private FireStationRepository repository;

    @Before
    public void setup() {

        repository = new FireStationRepository();

        String address = "123 Fake Street";
        String stationNumber = "1";

        FireStation fireStation = new FireStation(address, stationNumber);

        repository.createFireStation(fireStation);
    }

    @Test
    public void defaultConstructor() {
        //Arrange in setup
        //Act in setup

        //Assert
        assertNotNull(repository);
    }

    @Test
    public void findAll() {
        //Arrange in setup

        //Act
        List<FireStation> stations = repository.findAll();

        //Assert
        assertNotNull(stations);
        assertEquals(1, stations.size());
    }



    @Test
    public void getFireStation_FireStationExists_FireStationReturned() {
        //Arrange

        //Act
        FireStation result = repository.getFireStation("123 Fake Street");

        //Assert
        assertEquals("1", result.getStation());
    }

    @Test
    public void getFireStation_FireStationNonexistent_NothingHappens() {
        //Arrange
        String nonExistentAddress = "Allee de la Galaxie 93";

        //Act
        FireStation result = repository.getFireStation(nonExistentAddress);

        //Assert
        assertNull(result);
    }

    @Test
    public void createFireStation_AddressNonexistent_FireStationCreated() {
        //Arrange
        String address = "Allee de la Galaxie 93";
        String stationNumber = "3";

        FireStation fireStation = new FireStation(address, stationNumber);

        assertEquals(1, repository.findAll().size());

        //Act
        repository.createFireStation(fireStation);

        //Assert
        assertEquals(2, repository.findAll().size());
        assertEquals(stationNumber, repository.getFireStation(address).getStation());
    }

    @Test
    public void createFireStation_AddressExists_NothingHappens() {
        //Arrange
        String address = "123 Fake Street";
        String stationNumber = "3";

        FireStation fireStation = new FireStation(address, stationNumber);

        assertEquals(1, repository.findAll().size());

        //Act
        repository.createFireStation(fireStation);

        //Assert
        assertEquals(1, repository.findAll().size());
        assertNotEquals(stationNumber, repository.getFireStation(address).getStation());
    }

    @Test
    public void deleteFireStation_FireStationExists_FireStationDeleted() {
        //Arrange
        String address = "123 Fake Street";
        assertEquals(1, repository.findAll().size());

        //Act
        repository.deleteFireStation(address);

        //Assert
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void deleteFireStation_FireStationNonexistent_NothingHappens() {
        //Arrange
        String address = "Allee de la Galaxie 93";
        assertEquals(1, repository.findAll().size());

        //Act
        repository.deleteFireStation(address);

        //Assert
        assertEquals(1, repository.findAll().size());
    }
}
