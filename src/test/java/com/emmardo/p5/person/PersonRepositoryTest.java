package com.emmardo.p5.person;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PersonRepositoryTest {

    private PersonRepository repository;

    @Before
    public void setup() {

        repository = new PersonRepository();

        String firstName1 = "Fulano";
        String lastName1 = "Mengano";
        String address1 = "Alvarado 75";
        String city1 = "Salta";
        String zip1 = "4400";
        String phone1 = "12542";
        String email1 = "fulano@arnet.com.ar";

        String firstName2 = "John";
        String lastName2 = "Doe";
        String address2 = "7 Lemon Street";
        String city2 = "London";
        String zip2 = "W2 4AH";
        String phone2 = "56823";
        String email2 = "john@gbnet.co.uk";

        Person person1 = new Person();

        Person person2 = new Person();

        person1.setFirstName(firstName1);
        person1.setLastName(lastName1);
        person1.setAddress(address1);
        person1.setCity(city1);
        person1.setZip(zip1);
        person1.setPhone(phone1);
        person1.setEmail(email1);

        person2.setFirstName(firstName2);
        person2.setLastName(lastName2);
        person2.setAddress(address2);
        person2.setCity(city2);
        person2.setZip(zip2);
        person2.setPhone(phone2);
        person2.setEmail(email2);

        repository.createPerson(person1);

        repository.createPerson(person2);
    }

    @Test
    public void findAll() {
        //Arrange in setup

        //Act
        List<Person> persons = repository.findAll();

        //Assert
        assertEquals("Fulano", persons.get(0).getFirstName());
        assertEquals("John", persons.get(1).getFirstName());
    }

    @Test
    public void getPerson_PersonExists_PersonReturned() {
        //Arrange

        //Act
        Person person = repository.getPerson("Fulano", "Mengano");

        //Assert
        assertEquals(person, repository.getPerson("Fulano", "Mengano"));
    }

    @Test
    public void getPerson_FirstNameError_PersonReturned() {
        //Arrange

        //Act
        Person person = repository.getPerson("Heike", "Mengano");

        //Assert
        assertNotEquals(person, repository.getPerson("Fulano", "Mengano"));
    }

    @Test
    public void getPerson_LastNameError_PersonReturned() {
        //Arrange

        //Act
        Person person = repository.getPerson("Fulano", "Meyer");

        //Assert
        assertNotEquals(person, repository.getPerson("Fulano", "Mengano"));
    }

    @Test
    public void getPerson_PersonNonexistent_NullReturned() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Meyer";

        //Act
        Person person = repository.getPerson(firstName, lastName);

        //Assert
        assertNull(repository.getPerson(firstName, lastName));
    }

    @Test
    public void createPerson_PersonNotYetExistsButFirstNameSameAsThatOfExistingPerson_PersonCreated() {
        //Arrange
        String firstName = "John";
        String lastName = "Meyer";
        String address = "Danzigerstrasse 75";
        String city = "Berlin";
        String zip = "10342";
        String phone = "34572";
        String email = "heike@denet.com.de";

        Person person = new Person();

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setCity(city);
        person.setZip(zip);
        person.setPhone(phone);
        person.setEmail(email);

        assertEquals(2, repository.findAll().size());

        //Act
        repository.createPerson(person);

        Person personReturned = repository.findAll().get(2);

        //Assert
        assertEquals(personReturned, repository.getPerson("John", "Meyer"));
        assertEquals(3, repository.findAll().size());
    }

    @Test
    public void createPerson_PersonNotYetExistsButLastNameSameAsThatOfExistingPerson_PersonCreated() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Doe";
        String address = "Danzigerstrasse 75";
        String city = "Berlin";
        String zip = "10342";
        String phone = "34572";
        String email = "heike@denet.com.de";

        Person person = new Person();

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setCity(city);
        person.setZip(zip);
        person.setPhone(phone);
        person.setEmail(email);

        assertEquals(2, repository.findAll().size());

        //Act
        repository.createPerson(person);

        Person personReturned = repository.findAll().get(2);

        //Assert
        assertEquals(personReturned, repository.getPerson("Heike", "Doe"));
        assertEquals(3, repository.findAll().size());
    }

    @Test
    public void createPerson_PersonExists_NothingHappens() {
        //Arrange
        String firstName = "John";
        String lastName = "Doe";
        String address = "7 Lemon Street";
        String city = "London";
        String zip = "W2 4AH";
        String phone = "65872";
        String email = "john@gbnet.co.uk";

        Person person = new Person();

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setCity(city);
        person.setZip(zip);
        person.setPhone(phone);
        person.setEmail(email);

        assertEquals(2, repository.findAll().size());

        //Act
        repository.createPerson(person);

        //Assert
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void deletePerson_PersonExists_PersonDeleted() {
        //Arrange

        assertEquals(2, repository.findAll().size());
        assertEquals("Fulano", repository.findAll().get(0).getFirstName());

        //Act
        repository.deletePerson("Fulano", "Mengano");

        //Assert
        assertEquals(1, repository.findAll().size());
        assertEquals("John", repository.findAll().get(0).getFirstName());
    }

    @Test
    public void deletePerson_FirstNameExists_NothingHappens() {
        //Arrange

        assertEquals(2, repository.findAll().size());

        //Act
        repository.deletePerson("Fulano", "Meyer");

        //Assert
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void deletePerson_LastNameExists_NothingHappens() {
        //Arrange

        assertEquals(2, repository.findAll().size());

        //Act
        repository.deletePerson("Heike", "Mengano");

        //Assert
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void deletePerson_PersonNonexistent_NothingHappens() {
        //Arrange

        assertEquals(2, repository.findAll().size());

        //Act
        repository.deletePerson("Heike", "Meyer");

        //Assert
        assertEquals(2, repository.findAll().size());
    }
}
