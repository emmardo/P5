package com.emmardo.p5.medicalrecord;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class MedicalRecordTest {

    @Test
    public void defaultConstructor() {
        //Arrange
        String expectedFirstName = "";
        String expectedLastName = "";
        String expectedBirthDate = "";
        ArrayList<String> expectedMedications = new ArrayList<>();
        ArrayList<String> expectedAllergies = new ArrayList<>();

        //Act
        MedicalRecord record = new MedicalRecord();

        //Assert
        assertEquals(expectedFirstName, record.getFirstName());
        assertEquals(expectedLastName, record.getLastName());
        assertEquals(expectedBirthDate, record.getBirthdate());
        assertEquals(expectedMedications, record.getMedications());
        assertEquals(expectedAllergies, record.getAllergies());
    }

    @Test
    public void parameterizedConstructor() {
        //Arrange
        String expectedFirstName = "First";
        String expectedLastName = "Last";
        String expectedBirthDate = "01/01/1900";
        ArrayList<String> expectedMedications = new ArrayList<>(Arrays.asList("Medication 1", "Medication 2", "Medication 3"));
        ArrayList<String> expectedAllergies = new ArrayList<>(Arrays.asList("Allergy 1", "Allergy 2", "Allergy 3"));

        //Act
        MedicalRecord record = new MedicalRecord(expectedFirstName, expectedLastName, expectedBirthDate, expectedMedications, expectedAllergies);

        //Assert
        assertEquals(expectedFirstName, record.getFirstName());
        assertEquals(expectedLastName, record.getLastName());
        assertEquals(expectedBirthDate, record.getBirthdate());
        assertEquals(expectedMedications, record.getMedications());
        assertEquals(expectedAllergies, record.getAllergies());
    }

    @Test
    public void setFirstName() {
        //Arrange
        String expectedFirstName = "First";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setFirstName(expectedFirstName);

        //Assert
        assertEquals(expectedFirstName, record.getFirstName());
    }

    @Test
    public void setLastName() {
        //Arrange
        String expectedLastName = "Last";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setLastName(expectedLastName);

        //Assert
        assertEquals(expectedLastName, record.getLastName());
    }

    @Test
    public void setBirthDate() {
        //Arrange
        String expectedBirthDate = "01/01/1900";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setBirthdate(expectedBirthDate);

        //Assert
        assertEquals(expectedBirthDate, record.getBirthdate());
    }

    @Test
    public void setMedications() {
        //Arrange
        ArrayList<String> expectedMedications = new ArrayList<>(Arrays.asList("Medication 1", "Medication 2", "Medication 3"));

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setMedications(expectedMedications);

        //Assert
        assertEquals(expectedMedications, record.getMedications());
    }

    @Test
    public void setSingleMedication() {
        //Arrange
        String expectedMedication = "Medication 1";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setMedication(expectedMedication);

        //Assert
        assertThat(record.getMedications(), hasItem(expectedMedication));
    }

    @Test
    public void getSingleMedication_MedicationExists() {
        //Arrange
        String expectedMedication = "Medication 1";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setMedication(expectedMedication);

        String actualMedication = record.getMedication(expectedMedication);

        //Assert
        assertEquals(expectedMedication, actualMedication);
    }

    @Test
    public void getSingleMedication_MedicationDoesntExist() {
        //Arrange
        String expectedMedication = "Medication 1";
        String nonExistentMedication = "Medication 2";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setMedication(expectedMedication);

        String result = record.getMedication(nonExistentMedication);

        //Assert
        assertEquals(result, null);
    }

    @Test
    public void setAllergies() {
        //Arrange
        ArrayList<String> expectedAllergies = new ArrayList<>(Arrays.asList("Allergy 1", "Allergy 2", "Allergy 3"));

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setAllergies(expectedAllergies);

        //Assert
        assertEquals(expectedAllergies, record.getAllergies());
    }

    @Test
    public void setSingleAllergy() {
        //Arrange
        String expectedAllergy = "Allergy 1";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setAllergy(expectedAllergy);

        //Assert
        assertThat(record.getAllergies(), hasItem(expectedAllergy));
    }

    @Test
    public void getSingleAllergy_AllergyExists() {
        //Arrange
        String expectedAllergy = "Allergy 1";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setAllergy(expectedAllergy);

        String actualAllergy = record.getAllergy(expectedAllergy);

        //Assert
        assertEquals(expectedAllergy, actualAllergy);
    }

    @Test
    public void getSingleAllergy_AllergyDoesntExist() {
        //Arrange
        String expectedAllergy = "Allergy 1";
        String nonExistentAllergy = "Allergy 2";

        //Act
        MedicalRecord record = new MedicalRecord();
        record.setAllergy(expectedAllergy);

        String result = record.getAllergy(nonExistentAllergy);

        //Assert
        assertEquals(result, null);
    }
}
