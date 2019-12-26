package com.emmardo.p5.medicalrecord;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MedicalRecordRepositoryTest {

    MedicalRecordRepository repository;

    @Before
    public void setup() {

        repository = new MedicalRecordRepository();

        String firstName = "Fulano";
        String lastName = "Mengano";
        String birthDate = "11/09/2001";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        repository.createMedicalRecord(medicalRecord);
    }

    @Test
    public void findAllMedicalRecords() {
        //Arrange

        //Act
        List<MedicalRecord> records = repository.findAll();

        //Assert
        assertEquals(1 , records.size());
        assertEquals("11/09/2001", repository.getMedicalRecord("Fulano", "Mengano").getBirthdate());
    }

    @Test
    public void getMedicalRecord_MedicalRecordExists_MedicalRecordReturned() {
        //Arrange

        //Act
        MedicalRecord record = repository.getMedicalRecord("Fulano", "Mengano");

        //Assert
        assertEquals("11/09/2001", record.getBirthdate());
    }

    @Test
    public void getMedicalRecord_FirstNameError_NothingHappens() {
        //Arrange

        //Act
        MedicalRecord record = repository.getMedicalRecord("Heike", "Mengano");

        //Assert
        assertNull(record);
    }

    @Test
    public void getMedicalRecord_LastNameError_NothingHappens() {
        //Arrange

        //Act
        MedicalRecord record = repository.getMedicalRecord("Fulano", "Meyer");

        //Assert
        assertNull(record);
    }

    @Test
    public void getMedicalRecord_MedicalRecordNonexistent_NothingHappens() {
        //Arrange

        //Act
        MedicalRecord record = repository.getMedicalRecord("Heike", "Meyer");

        //Assert
        assertNull(record);
    }

    @Test
    public void createNewMedicalRecord_MDNonexistentButFirstNameSameAsThatOfExistingMD_MedicalRecordCreated() {
        //Arrange
        String firstName = "Fulano";
        String lastName = "Meyer";
        String birthDate = "01/01/2000";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        assertEquals(1, repository.findAll().size());

        //Act
        repository.createMedicalRecord(medicalRecord);

        MedicalRecord record = repository.getMedicalRecord("Fulano", "Meyer");

        //Assert
        assertEquals(2, repository.findAll().size());
        assertEquals(birthDate, record.getBirthdate());
    }

    @Test
    public void createNewMedicalRecord_MDNonexistentButLastNameSameAsThatOfExistingMD_MedicalRecordCreated() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Mengano";
        String birthDate = "01/01/2000";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        assertEquals(1, repository.findAll().size());

        //Act
        repository.createMedicalRecord(medicalRecord);

        MedicalRecord record = repository.getMedicalRecord("Heike", "Mengano");

        //Assert
        assertEquals(2, repository.findAll().size());
        assertEquals(birthDate, record.getBirthdate());
    }

    @Test
    public void createNewMedicalRecord_MDNonexistentAndFirstAndLastNameNonexistent_MedicalRecordCreated() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Meyer";
        String birthDate = "01/01/2000";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        assertEquals(1, repository.findAll().size());

        //Act
        repository.createMedicalRecord(medicalRecord);

        MedicalRecord record = repository.getMedicalRecord("Heike", "Meyer");

        //Assert
        assertEquals(2, repository.findAll().size());
        assertEquals(birthDate, record.getBirthdate());
    }

    @Test
    public void createNewMedicalRecord_MedicalRecordExists_NothingHappens() {
        //Arrange
        String firstName = "Fulano";
        String lastName = "Mengano";
        String birthDate = "01/01/2000";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        assertEquals(1, repository.findAll().size());

        //Act
        repository.createMedicalRecord(medicalRecord);

        //Assert
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void deleteMedicalRecord_MedicalRecordExists_MedicalRecordDeleted() {
        //Arrange
        assertEquals(1, repository.findAll().size());

        //Act
        repository.deleteMedicalRecord("Fulano", "Mengano");

        //Assert
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void deleteMedicalRecord_FirstNameExists_NothingHappens() {
        //Arrange
        assertEquals(1, repository.findAll().size());

        //Act
        repository.deleteMedicalRecord("Fulano", "Meyer");

        //Assert
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void deleteMedicalRecord_LastNameExists_NothingHappens() {
        //Arrange
        assertEquals(1, repository.findAll().size());

        //Act
        repository.deleteMedicalRecord("Heike", "Mengano");

        //Assert
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void deleteMedicalRecord_MedicalRecordNonexistent_NothingHappens() {
        //Arrange
        assertEquals(1, repository.findAll().size());

        //Act
        repository.deleteMedicalRecord("Heike", "Meyer");

        //Assert
        assertEquals(1, repository.findAll().size());
    }
}
