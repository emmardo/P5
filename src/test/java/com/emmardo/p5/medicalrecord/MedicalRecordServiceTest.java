package com.emmardo.p5.medicalrecord;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MedicalRecordServiceTest {

    private MedicalRecordRepository repository;
    private MedicalRecordService service;

    @Before
    public void setup() {

        repository = new MedicalRecordRepository();
        service = new MedicalRecordService(repository);

        String firstName1 = "Fulano";
        String lastName1 = "Mengano";
        String birthDate1 = "11/09/2002";
        List<String> medications1 = new ArrayList<>();
        List<String> allergies1 = new ArrayList<>();

        medications1.add("Medication1");
        medications1.add("Medication2");

        allergies1.add("Allergy1");
        allergies1.add("Allergy2");

        String firstName2 = "John";
        String lastName2 = "Doe";
        String birthDate2 = "11/09/1901";
        List<String> medications2 = new ArrayList<>();
        List<String> allergies2 = new ArrayList<>();

        medications2.add("MedicationA");
        medications2.add("MedicationB");

        allergies2.add("AllergyA");
        allergies2.add("AllergyB");

        MedicalRecord medicalRecord1 = new MedicalRecord(firstName1, lastName1, birthDate1, medications1, allergies1);
        MedicalRecord medicalRecord2 = new MedicalRecord(firstName2, lastName2, birthDate2, medications2, allergies2);

        repository.createMedicalRecord(medicalRecord1);

        repository.createMedicalRecord(medicalRecord2);
    }

    @Test
    public void getAllMedicalRecords() {
        //Arrange in setup

        //Act
        List<MedicalRecord> records = service.getAllMedicalRecords();

        //Assert
        assertEquals(2, records.size());
    }

    @Test
    public void getMedicalRecord_MedicalRecordExists_MedicalRecordReturned() {
        //Arrange

        //Act
        MedicalRecord record = service.getMedicalRecord("Fulano", "Mengano");

        //Arrange
        assertEquals("11/09/2002", record.getBirthdate());
    }

    @Test
    public void getMedicalRecord_FirstNameError_NothingHappens() {
        //Arrange

        //Act
        MedicalRecord record = service.getMedicalRecord("Heike", "Mengano");

        //Arrange
        assertTrue(record.getFirstName().isEmpty());
    }

    @Test
    public void getMedicalRecord_LastNameError_NothingHappens() {
        //Arrange

        //Act
        MedicalRecord record = service.getMedicalRecord("Fulano", "Meyer");

        //Arrange
        assertTrue(record.getFirstName().isEmpty());
    }

    @Test
    public void getMedicalRecord_MedicalRecordNonexistent_NothingHappens() {
        //Arrange

        //Act
        MedicalRecord record = service.getMedicalRecord("Heike", "Meyer");

        //Arrange
        assertTrue(record.getFirstName().isEmpty());
    }

    @Test
    public void createMedicalRecord_PersonNonexistent_MedicalRecordCreated() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Meyer";
        String birthDate = "01/01/2000";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        assertEquals(2, service.getAllMedicalRecords().size());

        //Act
        service.createMedicalRecord(medicalRecord);

        MedicalRecord record = service.getMedicalRecord("Heike", "Meyer");

        //Arrange
        assertEquals(3, service.getAllMedicalRecords().size());
        assertEquals(birthDate, record.getBirthdate());
    }

    @Test
    public void createMedicalRecord_FirstNameAlreadyExistst_MedicalRecordCreated() {
        //Arrange
        String firstName = "Fulano";
        String lastName = "Meyer";
        String birthDate = "01/01/2000";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        assertEquals(2, service.getAllMedicalRecords().size());

        //Act
        service.createMedicalRecord(medicalRecord);

        MedicalRecord record = service.getMedicalRecord("Fulano", "Meyer");

        //Arrange
        assertEquals(3, service.getAllMedicalRecords().size());
        assertEquals(birthDate, record.getBirthdate());
    }

    @Test
    public void createMedicalRecord_LastNameAlreadyExistst_MedicalRecordCreated() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Mengano";
        String birthDate = "01/01/2000";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        assertEquals(2, service.getAllMedicalRecords().size());

        //Act
        service.createMedicalRecord(medicalRecord);

        MedicalRecord record = service.getMedicalRecord("Heike", "Mengano");

        //Arrange
        assertEquals(3, service.getAllMedicalRecords().size());
        assertEquals(birthDate, record.getBirthdate());
    }

    @Test
    public void createMedicalRecord_MedicalRecordAlreadyExistst_NothingHappens() {
        //Arrange
        String firstName = "Fulano";
        String lastName = "Mengano";
        String birthDate = "11/09/2001";
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);

        assertEquals(2, service.getAllMedicalRecords().size());

        //Act
        service.createMedicalRecord(medicalRecord);


        //Arrange
        assertEquals(2, service.getAllMedicalRecords().size());
    }

    @Test
    public void updateMedicalRecord_MedicalRecordExists_MedicalRecordUpdated() {
        //Arrange
        String firstName = "Fulano";
        String lastName = "Mengano";
        String newBirthDate = "01/01/2000";
        List<String> newMedications = new ArrayList<>();
        List<String> newAllergies = new ArrayList<>();

        String firstMedication = "MedicationI";
        String secondMedication = "MedicationII";

        String firstAllergy = "Allergy.";
        String secondAllergy = "Allergy..";

        newMedications.add(firstMedication);
        newMedications.add(secondMedication);

        newAllergies.add(firstAllergy);
        newAllergies.add(secondAllergy);

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, newBirthDate, newMedications, newAllergies);

        assertEquals("11/09/2002", service.getMedicalRecord(firstName, lastName).getBirthdate());

        //Act
        service.updateMedicalRecord(medicalRecord);
        MedicalRecord record = service.getMedicalRecord(firstName, lastName);

        //Arrange
        assertEquals(newBirthDate, record.getBirthdate());
        assertEquals(secondMedication, service.getMedicalRecord(firstName, lastName).getMedications().get(1));
        assertEquals(firstAllergy, service.getMedicalRecord(firstName, lastName).getAllergies().get(0));
    }

    @Test
    public void updateMedicalRecord_FirstNameError_NothingHappens() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Mengano";
        String newBirthDate = "01/01/2000";
        List<String> newMedications = new ArrayList<>();
        List<String> newAllergies = new ArrayList<>();

        String firstMedication = "MedicationI";
        String secondMedication = "MedicationII";

        String firstAllergy = "Allergy.";
        String secondAllergy = "Allergy..";

        newMedications.add(firstMedication);
        newMedications.add(secondMedication);

        newAllergies.add(firstAllergy);
        newAllergies.add(secondAllergy);

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, newBirthDate, newMedications, newAllergies);

        assertEquals("11/09/2002", service.getMedicalRecord("Fulano", "Mengano").getBirthdate());

        //Act
        service.updateMedicalRecord(medicalRecord);
        MedicalRecord record = service.getMedicalRecord("Fulano", "Mengano");

        //Arrange
        assertNotEquals(newBirthDate, record.getBirthdate());
        assertNull(service.getMedicalRecord("Fulano", "Mengano").getAllergy(firstAllergy));
        assertNull(service.getMedicalRecord("Fulano", "Mengano").getMedication(secondMedication));
    }

    @Test
    public void updateMedicalRecord_LastNameError_NothingHappens() {
        //Arrange
        String firstName = "Fulano";
        String lastName = "Meyer";
        String newBirthDate = "01/01/2000";
        List<String> newMedications = new ArrayList<>();
        List<String> newAllergies = new ArrayList<>();

        String firstMedication = "MedicationI";
        String secondMedication = "MedicationII";

        String firstAllergy = "Allergy.";
        String secondAllergy = "Allergy..";

        newMedications.add(firstMedication);
        newMedications.add(secondMedication);

        newAllergies.add(firstAllergy);
        newAllergies.add(secondAllergy);

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, newBirthDate, newMedications, newAllergies);

        assertEquals("11/09/2002", service.getMedicalRecord("Fulano", "Mengano").getBirthdate());

        //Act
        service.updateMedicalRecord(medicalRecord);
        MedicalRecord record = service.getMedicalRecord("Fulano", "Mengano");

        //Arrange
        assertNotEquals(newBirthDate, record.getBirthdate());
        assertNull(service.getMedicalRecord("Fulano", "Mengano").getAllergy(firstAllergy));
        assertNull(service.getMedicalRecord("Fulano", "Mengano").getMedication(secondMedication));
    }

    @Test
    public void updateMedicalRecord_MedicalRecordInexistent_NothingHappens() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Meyer";
        String newBirthDate = "01/01/2000";
        List<String> newMedications = new ArrayList<>();
        List<String> newAllergies = new ArrayList<>();

        String firstMedication = "MedicationI";
        String secondMedication = "MedicationII";

        String firstAllergy = "Allergy.";
        String secondAllergy = "Allergy..";

        newMedications.add(firstMedication);
        newMedications.add(secondMedication);

        newAllergies.add(firstAllergy);
        newAllergies.add(secondAllergy);

        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, newBirthDate, newMedications, newAllergies);

        assertEquals("11/09/2002", service.getMedicalRecord("Fulano", "Mengano").getBirthdate());

        //Act
        service.updateMedicalRecord(medicalRecord);
        MedicalRecord record = service.getMedicalRecord("Fulano", "Mengano");

        //Arrange
        assertNotEquals(newBirthDate, record.getBirthdate());
        assertNull(service.getMedicalRecord("Fulano", "Mengano").getAllergy(firstAllergy));
        assertNull(service.getMedicalRecord("Fulano", "Mengano").getMedication(secondMedication));
    }

    @Test
    public void deleteMedicalRecord_MedicalRecordExists_MedicalRecordDeleted() {
        //Arrange
        String firstName = "Fulano";
        String lastName = "Mengano";
        assertEquals(2, repository.findAll().size());

        //Act
        service.deleteMedicalRecord(firstName, lastName);

        //Assert
        assertEquals(1, repository.findAll().size());
        assertTrue(repository.findAll().get(0).getFirstName() != firstName);
    }

    @Test
    public void deleteMedicalRecord_FirstNameError_NothingHappens() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Mengano";
        assertEquals(2, service.getAllMedicalRecords().size());

        //Act
        service.deleteMedicalRecord(firstName, lastName);

        //Assert
        assertEquals(2, service.getAllMedicalRecords().size());
        assertNotNull(service.getMedicalRecord("Fulano", lastName));
    }

    @Test
    public void deleteMedicalRecord_LastNameError_NothingHappens() {
        //Arrange
        String firstName = "Fulano";
        String lastName = "Meyer";
        assertEquals(2, service.getAllMedicalRecords().size());

        //Act
        service.deleteMedicalRecord(firstName, lastName);

        //Assert
        assertEquals(2, service.getAllMedicalRecords().size());
        assertNotNull(service.getMedicalRecord(firstName, "Mengano"));
    }

    @Test
    public void deleteMedicalRecord_MedicalRecordInexistent_NothingHappens() {
        //Arrange
        String firstName = "Heike";
        String lastName = "Meyer";
        assertEquals(2, service.getAllMedicalRecords().size());
        assertTrue(service.getMedicalRecord(firstName, lastName).getFirstName().isEmpty());


        //Act
        service.deleteMedicalRecord(firstName, lastName);

        //Assert
        assertEquals(2, service.getAllMedicalRecords().size());
    }
}
