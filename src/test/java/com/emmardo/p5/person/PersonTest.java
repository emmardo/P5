package com.emmardo.p5.person;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void defaultConstructor() {
        //Arrange
        String expectedFirstName = "";
        String expectedLastName = "";
        String expectedAddress = "";
        String expectedCity = "";
        String expectedZip = "";
        String expectedPhone = "";
        String expectedEmail = "";
        String expectedAge = "";
        List<String> expectedMedications = null;
        List<String> expectedAllergies = null;
        String expectedNumberOfAdults = "";
        String expectedNumberOfChildren = "";

        //Act
        Person person = new Person();

        //Assert
        assertEquals(expectedFirstName, person.getFirstName());
        assertEquals(expectedLastName, person.getLastName());
        assertEquals(expectedAddress, person.getAddress());
        assertEquals(expectedCity, person.getCity());
        assertEquals(expectedZip, person.getZip());
        assertEquals(expectedPhone, person.getPhone());
        assertEquals(expectedEmail, person.getEmail());
        assertEquals(expectedAge, person.getAge());
        assertEquals(expectedMedications, person.getMedications());
        assertEquals(expectedAllergies, person.getAllergies());
        assertEquals(expectedNumberOfAdults, person.getNumberOfAdults());
        assertEquals(expectedNumberOfChildren, person.getNumberOfChildren());
    }

    @Test
    public void parameterizedConstructor() {
        //Arrange
        String expectedFirstName = "First";
        String expectedLastName = "Last";
        String expectedAddress = "123 Fake Street";
        String expectedCity = "City";
        String expectedZip = "12345";
        String expectedPhone = "1";
        String expectedEmail = "a@b.com";

        //Act
        Person person = new Person(expectedFirstName, expectedLastName, expectedAddress, expectedCity, expectedZip, expectedPhone, expectedEmail);

        //Assert
        assertEquals(expectedFirstName, person.getFirstName());
        assertEquals(expectedLastName, person.getLastName());
        assertEquals(expectedAddress, person.getAddress());
        assertEquals(expectedCity, person.getCity());
        assertEquals(expectedZip, person.getZip());
        assertEquals(expectedPhone, person.getPhone());
        assertEquals(expectedEmail, person.getEmail());
    }

    @Test
    public void setFirstName() {
        //Arrange
        String expectedFirstName = "First";

        //Act
        Person person = new Person();
        person.setFirstName(expectedFirstName);

        //Assert
        assertEquals(expectedFirstName, person.getFirstName());
    }

    @Test
    public void setLastName() {
        //Arrange
        String expectedLastName = "Last";

        //Act
        Person person = new Person();
        person.setLastName(expectedLastName);

        //Assert
        assertEquals(expectedLastName, person.getLastName());
    }

    @Test
    public void setAddress() {
        //Arrange
        String expectedAddress = "123 Fake Street";

        //Act
        Person person = new Person();
        person.setAddress(expectedAddress);

        //Assert
        assertEquals(expectedAddress, person.getAddress());
    }

    @Test
    public void setCity() {
        //Arrange
        String expectedCity = "City";

        //Act
        Person person = new Person();
        person.setCity(expectedCity);

        //Assert
        assertEquals(expectedCity, person.getCity());
    }

    @Test
    public void setZip() {
        //Arrange
        String expectedZip = "zip";

        //Act
        Person person = new Person();
        person.setZip(expectedZip);

        //Assert
        assertEquals(expectedZip, person.getZip());
    }

    @Test
    public void setPhone() {
        //Arrange
        String expectedPhone = "1";

        //Act
        Person person = new Person();
        person.setPhone(expectedPhone);

        //Assert
        assertEquals(expectedPhone, person.getPhone());
    }

    @Test
    public void setEmail() {
        //Arrange
        String expectedEmail = "a@b.com";

        //Act
        Person person = new Person();
        person.setEmail(expectedEmail);

        //Assert
        assertEquals(expectedEmail, person.getEmail());
    }

    @Test
    public void setAge() {
        //Arrange
        String expectedAge = "1";

        //Act
        Person person = new Person();
        person.setAge("1");

        //Assert
        assertEquals(expectedAge, person.getAge());
    }

    @Test
    public void setMedications() {
        //Arrange
        List<String> expectedMedications = new ArrayList<>();
        expectedMedications.add("Medication1");
        expectedMedications.add("Medication2");

        //Act
        Person person = new Person();
        person.setMedications(expectedMedications);

        //Assert
        assertEquals(expectedMedications, person.getMedications());
    }

    @Test
    public void setAllergies() {
        //Arrange
        List<String> expectedAllergies = new ArrayList<>();
        expectedAllergies.add("Allergy1");
        expectedAllergies.add("Allergy2");

        //Act
        Person person = new Person();
        person.setAllergies(expectedAllergies);

        //Assert
        assertEquals(expectedAllergies, person.getAllergies());
    }

    @Test
    public void setNumberOfAdults() {
        //Arrange
        String expectedNumberOfAdults = "2";

        //Act
        Person person = new Person();
        person.setNumberOfAdults(expectedNumberOfAdults);

        //Assert
        assertEquals(expectedNumberOfAdults, person.getNumberOfAdults());
    }

    @Test
    public void setNumberOfChildren() {
        //Arrange
        String expectedNumberOfChildren = "2";

        //Act
        Person person = new Person();
        person.setNumberOfChildren(expectedNumberOfChildren);

        //Assert
        assertEquals(expectedNumberOfChildren, person.getNumberOfChildren());
    }
}
