package com.emmardo.p5.person;

import com.emmardo.p5.medicalrecord.MedicalRecord;
import com.emmardo.p5.medicalrecord.MedicalRecordRepository;
import com.emmardo.p5.medicalrecord.MedicalRecordService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonServiceTest {

    private PersonRepository personRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private MedicalRecordService medicalRecordService;
    private PersonService personService;


    @Before
    public void setup() {

        personRepository = new PersonRepository();
        medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordService = new MedicalRecordService(medicalRecordRepository);
        personService = new PersonService(personRepository, medicalRecordService);

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

        personService.createPerson(person1);

        personService.createPerson(person2);

        String birthDate1 = "11/09/2002";
        List<String> medications1 = new ArrayList<>();
        List<String> allergies1 = new ArrayList<>();

        medications1.add("Medication1");
        medications1.add("Medication2");

        allergies1.add("Allergy1");
        allergies1.add("Allergy2");

        String birthDate2 = "11/09/1901";
        List<String> medications2 = new ArrayList<>();
        List<String> allergies2 = new ArrayList<>();

        medications2.add("MedicationA");
        medications2.add("MedicationB");

        allergies2.add("AllergyA");
        allergies2.add("AllergyB");

        MedicalRecord medicalRecord1 = new MedicalRecord(firstName1, lastName1, birthDate1, medications1, allergies1);
        MedicalRecord medicalRecord2 = new MedicalRecord(firstName2, lastName2, birthDate2, medications2, allergies2);

        medicalRecordRepository.createMedicalRecord(medicalRecord1);

        medicalRecordRepository.createMedicalRecord(medicalRecord2);
    }

    @Test
    public void getAllPersons() {
        //Arrange in setup

        //Act
        List<Person> persons = personService.getAllPersons();

        //Assert
        assertEquals("Fulano", persons.get(0).getFirstName());
        assertEquals("John", persons.get(1).getFirstName());
    }

    @Test
    public void getPerson_PersonExists_PersonReturned() {
        //Arrange

        //Act
        Person person = personService.getPerson("Fulano", "Mengano");

        //Assert
        assertEquals("Salta", person.getCity());
    }

    @Test
    public void getPerson_FirstNameError_NullReturned() {
        //Arrange

        //Act
        Person person = personService.getPerson("Heike", "Mengano");

        //Assert
        assertTrue(person.getFirstName().isEmpty());
    }

    @Test
    public void getPerson_LastNameError_NullReturned() {
        //Arrange

        //Act
        Person person = personService.getPerson("Fulano", "Meyer");

        //Assert
        assertTrue(person.getFirstName().isEmpty());
    }

    @Test
    public void getPerson_FirstAndLastNameError_NullReturned() {
        //Arrange

        //Act
        Person person = personService.getPerson("Heike", "Meyer");

        //Assert
        assertTrue(person.getFirstName().isEmpty());
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

        //Act
        personService.createPerson(person);

        Person personReturned = personService.getPerson("John", "Meyer");

        //Assert
        assertEquals("Berlin", personReturned.getCity());
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

        //Act
        personService.createPerson(person);

        Person personReturned = personService.getPerson("Heike", "Doe");

        //Assert
        assertEquals("Berlin", personReturned.getCity());
    }

    @Test
    public void createPerson_PersonExistsWithDifferentDetails_PersonNotCreated() {
        //Arrange
        String firstName = "John";
        String lastName = "Doe";
        String address = "12 Cromwell Road";
        String city = "London";
        String zip = "W2 8RE";
        String phone = "45627";
        String email = "john@gbnet.co.uk";

        Person person = new Person();

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setCity(city);
        person.setZip(zip);
        person.setPhone(phone);
        person.setEmail(email);

        //Act
        personService.createPerson(person);

        Person personReturned = personService.getPerson("John", "Doe");

        //Assert
        assertNotEquals(address, personReturned.getAddress());
    }

    @Test
    public void updatePerson_PersonExists_DetailsUpdated() {
        //Arrange
        String newAddress = "7 Lemon Street";
        String newCity = "London";
        String newZip = "W2 4AH";
        String newPhone = "56525";
        String sameEmail = "fulano@arnet.com.ar";

        Person person = new Person();

        person.setFirstName("Fulano");
        person.setLastName("Mengano");
        person.setAddress(newAddress);
        person.setCity(newCity);
        person.setZip(newZip);
        person.setPhone(newPhone);
        person.setEmail(sameEmail);

        assertEquals("Salta", personService.getPerson("Fulano", "Mengano")
                                        .getCity());
        assertEquals("4400", personService.getPerson("Fulano", "Mengano")
                                        .getZip());

        //Act
        personService.updatePerson(person);

        Person personReturned = personService.getPerson("Fulano", "Mengano");

        //Assert
        assertEquals("London", personReturned.getCity());
        assertEquals("W2 4AH", personService.getPerson("Fulano", "Mengano")
                                        .getZip());
    }

    @Test
    public void updatePerson_PersonNotYetExistsButFirstNameSameAsThatOfExistingPerson_NothingHappens() {
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

        //Act
        personService.updatePerson(person);

        Person personReturned = personService.getPerson(firstName, lastName);

        //Assert
        assertTrue(personReturned.getFirstName().isEmpty());
    }

    @Test
    public void updatePerson_PersonNotYetExistsButLastNameSameAsThatOfExistingPerson_NothingHappens() {
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

        //Act
        personService.updatePerson(person);

        Person personReturned = personService.getPerson(firstName, lastName);

        //Assert
        assertTrue(personReturned.getFirstName().isEmpty());
    }

    @Test
    public void deletePerson_PersonExists_PersonDeleted() {
        //Arrange
        assertEquals(2, personService.getAllPersons().size());
        assertEquals("Fulano", personService.getAllPersons().get(0).getFirstName());

        //Act
        personService.deletePerson("Fulano", "Mengano");

        //Assert
        assertEquals(1, personService.getAllPersons().size());
        assertEquals("John", personService.getAllPersons().get(0).getFirstName());
    }

    @Test
    public void deletePerson_FirstNameExists_NothingHappens() {
        //Arrange
        assertEquals(2, personService.getAllPersons().size());

        //Act
        personService.deletePerson("Fulano", "Meyer");

        //Assert
        assertEquals(2, personService.getAllPersons().size());
    }

    @Test
    public void deletePerson_LastNameExists_NothingHappens() {
        //Arrange
        assertEquals(2, personService.getAllPersons().size());

        //Act
        personService.deletePerson("Heike", "Mengano");

        //Assert
        assertEquals(2, personService.getAllPersons().size());
    }

    @Test
    public void deletePerson_PersonNonexistent_NothingHappens() {
        //Arrange
        String newFirstName = "Heike";
        String newLastName = "Meyer";

        assertEquals(2, personService.getAllPersons().size());
        assertEquals("Fulano", personService.getAllPersons().get(0).getFirstName());
        assertEquals("John", personService.getAllPersons().get(1).getFirstName());

        //Act
        personService.deletePerson(newFirstName, newLastName);

        //Assert
        assertEquals(2, personService.getAllPersons().size());
        assertEquals("Fulano", personService.getAllPersons().get(0).getFirstName());
        assertEquals("John", personService.getAllPersons().get(1).getFirstName());
    }

    @Test
    public void getPersonsLivingAtAddresses_AddressesExist_PersonsReturned() {
        //Arrange
        List<String> addresses = new ArrayList<>();

        addresses.add("Alvarado 75");
        addresses.add("7 Lemon Street");

        //Act
        List<Person> persons = personService.getPersonsLivingAtAddresses(addresses);

        //Assert
        assertEquals(2, persons.size());
        assertEquals("Fulano", persons.get(0).getFirstName());
        assertEquals("Doe", persons.get(1).getLastName());
    }

    @Test
    public void getPersonsAge_PersonExists_AgeReturned() {
        //Arrange

        //Act
        String age = personService.getPersonsAge(personService.getPerson("Fulano", "Mengano"));
        //Assert
        assertEquals("17", age);
    }

    @Test
    public void getPersonsAge_PersonNonexistent_NullReturned() {
        //Arrange
        Person newPerson = new Person();

        //Act
        String age = personService.getPersonsAge(newPerson);

        //Assert
        assertNull(age);
    }

    @Test
    public void getNumberOfAdultsInList_ListExists_NumberOfAdultsReturned() {
        //Arrange

        //Act
        String numberOfAdults = personService.getNumberOfAdultsInList(personService.getAllPersons());

        //Assert
        assertEquals("1", numberOfAdults);
    }

    @Test
    public void getNumberOfChildrenInList_ListExists_NumberOfAdultsReturned() {
        //Arrange

        //Act
        String numberOfChildren = personService.getNumberOfChildrenInList(personService.getAllPersons());

        //Assert
        assertEquals("1", numberOfChildren);
    }

    @Test
    public void getEmailsByCity_CityExistsInRepository_EmailsReturned() {
        //Arrange

        //Act
        List<String> emails = personService.getEmailsByCity("Salta");

        //Assert
        assertEquals("fulano@arnet.com.ar", emails.get(0));
    }

    @Test
    public void getEmailsByCity_CityInexistentInRepository_NullReturned() {
        //Arrange

        //Act
        List<String> emails = personService.getEmailsByCity("Berlin");

        //Assert
        assertEquals(0, emails.size());
    }
}
