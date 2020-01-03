package com.emmardo.p5.firestation;

import com.emmardo.p5.medicalrecord.MedicalRecord;
import com.emmardo.p5.medicalrecord.MedicalRecordRepository;
import com.emmardo.p5.medicalrecord.MedicalRecordService;
import com.emmardo.p5.person.Person;
import com.emmardo.p5.person.PersonRepository;
import com.emmardo.p5.person.PersonService;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class FireStationServiceTest {

    private PersonRepository personRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private FireStationRepository fireStationRepository;
    private MedicalRecordService medicalRecordService;
    private FireStationService fireStationService;
    private PersonService personService;

    @Before
    public void setup() {
        fireStationRepository = new FireStationRepository();
        personRepository = new PersonRepository();
        medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordService = new MedicalRecordService(medicalRecordRepository);
        personService = new PersonService(personRepository, medicalRecordService);
        fireStationService = new FireStationService(fireStationRepository, medicalRecordService, personService);

        String address1 = "Alvarado 75";
        String stationNumber1 = "1";

        String address2 = "Allee de la Galaxie 93";
        String stationNumber2 = "2";

        FireStation fireStation1 = new FireStation(address1, stationNumber1);
        FireStation fireStation2 = new FireStation(address2, stationNumber2);

        fireStationRepository.createFireStation(fireStation1);
        fireStationRepository.createFireStation(fireStation2);

        String firstName1 = "Fulano";
        String lastName1 = "Mengano";
        String city1 = "Salta";
        String zip1 = "4400";
        String phone1 = "12542";
        String email1 = "fulano@arnet.com.ar";

        String firstName2 = "John";
        String lastName2 = "Doe";
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

        personRepository.createPerson(person1);

        personRepository.createPerson(person2);

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
    public void getAllFireStations() {
        //Arrange in setup

        //Act
        List<FireStation> stations = fireStationService.getAllFireStations();

        //Assert
        assertEquals(2, stations.size());
    }

    @Test
    public void getFireStation_FireStationExists_FireStationReturned() {
        //Arrange

        //Act
        FireStation result = fireStationService.getFireStation("Alvarado 75");

        //Assert
        assertEquals("1", result.getStation());
    }

    @Test
    public void getFireStation_FireStationNonexistent_NothingHappens() {
        //Arrange
        String nonExistentAddress = "Caseros 268";

        //Act
        FireStation result = fireStationService.getFireStation(nonExistentAddress);

        //Assert
        assertNull(result);
    }

    @Test
    public void createFireStation_AddressNonexistent_FireStationCreated() {
        //Arrange
        String address = "Caseros 268";
        String stationNumber = "3";

        FireStation fireStation = new FireStation(address, stationNumber);

        assertEquals(2, fireStationService.getAllFireStations().size());

        //Act
        fireStationService.createFireStation(fireStation);

        //Assert
        assertEquals(3, fireStationService.getAllFireStations().size());
        assertEquals(stationNumber, fireStationService.getFireStation(address).getStation());
    }

    @Test
    public void createFireStation_AddressExists_NothingHappens() {
        //Arrange
        String address = "Alvarado 75";
        String stationNumber = "3";

        FireStation fireStation = new FireStation(address, stationNumber);

        assertEquals(2, fireStationService.getAllFireStations().size());

        //Act
        fireStationService.createFireStation(fireStation);

        //Assert
        assertEquals(2, fireStationService.getAllFireStations().size());
        assertNotEquals(stationNumber, fireStationService.getFireStation(address).getStation());
    }

    @Test
    public void createFireStation_AddressNonexistentButStationNumberExists_FireStationCreated() {
        //Arrange
        String address = "Caseros 268";
        String stationNumber = "1";

        FireStation fireStation = new FireStation(address, stationNumber);

        assertEquals(2, fireStationService.getAllFireStations().size());
        assertEquals("1", fireStationService.getFireStation("Alvarado 75").getStation());

        //Act
        fireStationService.createFireStation(fireStation);

        //Assert
        assertEquals(3, fireStationService.getAllFireStations().size());
        assertEquals("1", fireStationService.getFireStation("Caseros 268").getStation());
    }

    @Test
    public void createFireStation_FireStationAlreadyExists_NothingHappens() {
        //Arrange
        String address = "Alvarado 75";
        String stationNumber = "1";

        FireStation fireStation = new FireStation(address, stationNumber);

        assertEquals(2, fireStationService.getAllFireStations().size());

        //Act
        fireStationService.createFireStation(fireStation);

        //Assert
        assertEquals(2, fireStationService.getAllFireStations().size());
    }

    @Test
    public void updateStation_AddressExists_StationNumberUpdated() {
        //Arrange
        String currentAddress = "Alvarado 75";

        String newStationNumber = "7";

        FireStation fireStation = new FireStation(currentAddress, newStationNumber);

        //Act
        fireStationService.updateStation(fireStation);

        //Assert
        assertEquals(newStationNumber, fireStationService.getFireStation(currentAddress).getStation());
    }

    @Test
    public void updateStation_AddressInexistent_NothingHappens() {
        //Arrange
        String currentAddress = "1 Fake Street";

        String newStationNumber = "2";

        FireStation fireStation = new FireStation(currentAddress, newStationNumber);

        //Act
        fireStationService.updateStation(fireStation);

        //Assert
        assertNull(fireStationService.getFireStation(currentAddress));
    }

    @Test
    public void deleteFireStation_FireStationExists_FireStationDeleted() {
        //Arrange
        String address = "Alvarado 75";
        assertEquals(2, fireStationService.getAllFireStations().size());

        //Act
        fireStationService.deleteStation(address);

        //Assert
        assertNull(fireStationService.getFireStation(address));
        assertEquals(1, fireStationService.getAllFireStations().size());
    }

    @Test
    public void deleteFireStation_FireStationNonexistent_NothingHappens() {
        //Arrange
        String address = "Caseros 268";
        assertEquals(2, fireStationService.getAllFireStations().size());

        //Act
        fireStationService.deleteStation(address);

        //Assert
        assertEquals(2, fireStationService.getAllFireStations().size());
    }

    @Test
    public void getAddressesCoveredByFireStation_StationExists_AddressesReturned() {
        //Arrange
        String fireStationRequired = "1";

        String address3 = "1 Fake Street";
        String stationNumber3 = "1";

        String address4 = "2 Fake Street";
        String stationNumber4 = "1";

        FireStation fireStation3 = new FireStation(address3, stationNumber3);
        FireStation fireStation4 = new FireStation(address4, stationNumber4);

        fireStationService.createFireStation(fireStation3);
        fireStationService.createFireStation(fireStation4);

        //Act
        List<String> addresses = fireStationService.getAddressesCoveredByFireStation(fireStationRequired);

        //Assert
        assertEquals(3, addresses.size());
        assertEquals(address3, addresses.get(1));
        assertEquals(address4, addresses.get(2));
        assertEquals("Alvarado 75", addresses.get(0));

    }

    @Test
    public void getAddressesCoveredByFireStation_StationInexistent_AddressesListReturnedEmpty() {
        //Arrange
        String fireStationRequired = "7";

        //Act
        List<String> addresses = fireStationService.getAddressesCoveredByFireStation(fireStationRequired);

        //Assert
        assertTrue(addresses.isEmpty());

    }

    @Test
    public void getPersonsByStation_StationExists_PersonReturned() {
        //Arrange
        String station = "1";

        //Act
        List<Person> list = fireStationService.getPersonsByStation(station);
        //Assert
        assertTrue(list.stream().filter(p -> p.getFirstName().equals("Fulano") && p.getLastName().equals("Mengano")).findFirst().isPresent());
    }

    @Test
    public void getPersonsByStation_StationInexistent_PersonsListReturnedEmpty() {
        //Arrange
        String station = "7";

        //Act
        List<Person> list = fireStationService.getPersonsByStation(station);
        //Assert
        assertTrue(list.isEmpty());
    }

    @Test
    public void getAdultsAndChildrenServicedByFireStation_StationExists_AnyAdultsAndChildrenCoveredReturned() {
        //Arrange
        List<Person> list = new ArrayList<>();

        String station = "1";

        //Act
        list = fireStationService.getAdultsAndChildrenServicedByFireStation(station);

        //Assert
        assertEquals("Fulano", list.get(0).getFirstName());
    }

    @Test
    public void getAdultsAndChildrenServicedByFireStation_StationInexistent_AdultsAndChildrenListReturnedEmpty() {
        //Arrange
        List<Person> list = new ArrayList<>();

        String station = "7";

        //Act
        list = fireStationService.getAdultsAndChildrenServicedByFireStation(station);

        //Assert
        assertTrue(list.isEmpty());
    }

    @Test
    public void getPhonesByStation_StationExists_PhonesReturned() {
        //Arrange
        List<String> list = new ArrayList<>();

        String station = "1";

        //Act
        list = fireStationService.getPhonesByStation(station);

        //Assert
        assertEquals("12542", list.get(0));
    }

    @Test
    public void getPhonesByStation_StationInexistent_PhonesListReturnedEmpty() {
        //Arrange
        List<String> list = new ArrayList<>();

        String station = "7";

        //Act
        list = fireStationService.getPhonesByStation(station);

        //Assert
        assertTrue(list.isEmpty());
    }

    @Test
    public void getFireStationAndPersonsByAddress_AddressExists_FireStationAndPersonsReturned() {
        //Arrange
        HashMap<String, List<Person>> hashMap = new HashMap<>();

        String address = "Alvarado 75";

        //Act
        hashMap = fireStationService.getFireStationAndPersonsByAddress(address);

        //Assert
        assertEquals("Fulano", hashMap.get("1").get(0).getFirstName());
    }

    @Test
    public void getFireStationAndPersonsByAddress_AddressInexistent_FireStationAndPersonsHashMapReturnedEmpty() {
        //Arrange
        HashMap<String, List<Person>> hashMap = new HashMap<>();

        String address = "Danziger Strasse 31";

        //Act
        hashMap = fireStationService.getFireStationAndPersonsByAddress(address);

        //Assert
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void getHouseholdsCoveredByStations_AtLeastOneStationExists_HouseholdsListReturned() {
        //Arrange
        HashMap<String, List<Person>> hashMap = new HashMap<>();

        String station1 = "1";
        String station2 = "7";

        List<String> stations = new ArrayList<>();
        stations.add(station1);
        stations.add(station2);

        //Act
        hashMap = fireStationService.getHouseholdsCoveredByStations(stations);


        //Assert
        assertEquals("Fulano", hashMap.get("FireStation: 1. Address: Alvarado 75").get(0).getFirstName());
    }

    @Test
    public void getHouseholdsCoveredByStations_NoStationExists_HouseholdsListReturnedEmpty() {
        //Arrange
        HashMap<String, List<Person>> hashMap = new HashMap<>();

        String station1 = "8";
        String station2 = "9";

        List<String> stations = new ArrayList<>();
        stations.add(station1);
        stations.add(station2);

        //Act
        hashMap = fireStationService.getHouseholdsCoveredByStations(stations);

        //Assert
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void getPersonsInformation_PersonExists_PersonsInformationReturned() {
        //Arrange
        String firstName = "Fulano";

        String lastName = "Mengano";

        Person person = new Person();

        //Act
        person = fireStationService.getPersonsInformation(firstName, lastName);

        //Assert
        assertEquals("Fulano", person.getFirstName());
    }

    @Test
    public void getPersonsInformation_FirstNameError_EmptyPersonReturned() {
        //Arrange
        String firstName = "Hans";

        String lastName = "Mengano";

        Person person = new Person();

        //Act
        person = fireStationService.getPersonsInformation(firstName, lastName);

        //Assert
        assertEquals("", person.getFirstName());
    }

    @Test
    public void getPersonsInformation_LastNameError_EmptyPersonReturned() {
        //Arrange
        String firstName = "Fulano";

        String lastName = "Dieter";

        Person person = new Person();

        //Act
        person = fireStationService.getPersonsInformation(firstName, lastName);

        //Assert
        assertEquals("", person.getLastName());
    }

    @Test
    public void getPersonsInformation_PersonInexistent_EmptyPersonReturned() {
        //Arrange
        String firstName = "Hans";

        String lastName = "Dieter";

        Person person = new Person();

        //Act
        person = fireStationService.getPersonsInformation(firstName, lastName);

        //Assert
        assertEquals("", person.getFirstName());
        assertEquals("", person.getLastName());
    }

    @Test
    public void getChildrenLivingAtAddressAsWellAsAnyAdult_AddressExistsAndChildrenDoLiveThere_ChildrenAndAdultsLivingAtAddressReturned() {
        //Arrange
        String address = "Alvarado 75";

        List<Person> list = new ArrayList<>();

        //Act
        list = fireStationService.getChildrenLivingAtAddressAsWellAsAnyAdult(address);

        //Assert
        assertEquals("Fulano", list.get(0).getFirstName());
    }

    @Test
    public void getChildrenLivingAtAddressAsWellAsAnyAdult_AddressExistsButNoChildrenLiveThere_ChildrenAndAdultsLivingAtAddressListReturnedEmpty() {
        //Arrange
        String address = "Allee de la Galaxie 93";

        List<Person> list = new ArrayList<>();

        //Act
        list = fireStationService.getChildrenLivingAtAddressAsWellAsAnyAdult(address);

        //Assert
        assertTrue(list.isEmpty());
    }

    @Test
    public void getChildrenLivingAtAddressAsWellAsAnyAdult_AddressInexistent_ChildrenAndAdultsLivingAtAddressListReturnedEmpty() {
        //Arrange
        String address = "Danziger Strasse 31";

        List<Person> list = new ArrayList<>();

        //Act
        list = fireStationService.getChildrenLivingAtAddressAsWellAsAnyAdult(address);

        //Assert
        assertTrue(list.isEmpty());
    }
}
