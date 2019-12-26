package com.emmardo.p5.firestation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FireStationTest {

    @Test
    public void defaultConstructor() {
        //Arrange
        String expectedAddress = "";
        String expectedStation = "";

        //Act
        FireStation station = new FireStation();

        //Assert
        assertEquals(expectedAddress, station.getAddress());
        assertEquals(expectedStation, station.getStation());
    }

    @Test
    public void parameterizedConstructor() {
        //Arrange
        String expectedAddress = "123 Fake Street";
        String expectedStation = "1";

        //Act
        FireStation station = new FireStation(expectedAddress, expectedStation);

        //Assert
        assertEquals(expectedAddress, station.getAddress());
        assertEquals(expectedStation, station.getStation());
    }

    @Test
    public void setAddress() {
        //Arrange
        String expectedAddress = "123 Fake Street";

        //Act
        FireStation station = new FireStation("", "");
        station.setAddress(expectedAddress);

        //Assert
        assertEquals(expectedAddress, station.getAddress());
    }

    @Test
    public void setStation() {
        //Arrange
        String expectedStation = "1";

        //Act
        FireStation station = new FireStation("", "1");
        station.setStation(expectedStation);

        //Assert
        assertEquals(expectedStation, station.getStation());
    }
}
