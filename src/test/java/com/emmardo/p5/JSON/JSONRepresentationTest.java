package com.emmardo.p5.JSON;

import com.emmardo.p5.firestation.FireStation;
import com.emmardo.p5.medicalrecord.MedicalRecord;
import com.emmardo.p5.person.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JSONRepresentationTest {

    private JSONRepresentation jsonRepresentation;

    @Before
    public void setup() {

        jsonRepresentation = new JSONRepresentation();
    }

    @Test
    public void setPersons_PersonsValid_PersonsSet() {
        //Arrange
        Person person1 = new Person();
        Person person2 = new Person();

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);

        //Act
        jsonRepresentation.setPersons(list);

        //Assert
        assertEquals(person1, jsonRepresentation.getPersons().get(0));
        assertEquals(person2, jsonRepresentation.getPersons().get(1));
    }

    @Test
    public void setPersons_PersonsNull_PersonsListSet() {
        //Arrange
        Person person1 = null;
        Person person2 = null;

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);

        //Act
        jsonRepresentation.setPersons(list);

        //Assert
        assertEquals(person1, jsonRepresentation.getPersons().get(0));
        assertEquals(person2, jsonRepresentation.getPersons().get(1));
    }

    @Test
    public void setPersons_ListEmpty_PersonsListSet() {
        //Arrange
        List<Person> list = new ArrayList<>();

        //Act
        jsonRepresentation.setPersons(list);

        //Assert
        assertEquals(list, jsonRepresentation.getPersons());

    }

    @Test
    public void setFirestations_StationsValid_FireStationsSet() {
        //Arrange
        FireStation station1 = new FireStation();
        FireStation station2 = new FireStation();

        List<FireStation> list = new ArrayList<>();
        list.add(station1);
        list.add(station2);

        //Act
        jsonRepresentation.setFirestations(list);

        //Assert
        assertEquals(station1, jsonRepresentation.getFirestations().get(0));
        assertEquals(station2, jsonRepresentation.getFirestations().get(1));
    }

    @Test
    public void setFirestations_StationsNull_FireStationsSet() {
        //Arrange
        FireStation station1 = null;
        FireStation station2 = null;

        List<FireStation> list = new ArrayList<>();
        list.add(station1);
        list.add(station2);

        //Act
        jsonRepresentation.setFirestations(list);

        //Assert
        assertEquals(station1, jsonRepresentation.getFirestations().get(0));
        assertEquals(station2, jsonRepresentation.getFirestations().get(1));
    }

    @Test
    public void setFirestations_StationsListEmpty_EmptyListSet() {
        //Arrange
        List<FireStation> list = new ArrayList<>();

        //Act
        jsonRepresentation.setFirestations(list);

        //Assert
        assertEquals(list, jsonRepresentation.getFirestations());
    }

    @Test
    public void setMedicalrecords_MedicalRecordsValid_MedicalRecordsSet() {
        //Arrange
        MedicalRecord medicalRecord1 = new MedicalRecord();
        MedicalRecord medicalRecord2 = new MedicalRecord();

        List<MedicalRecord> list = new ArrayList<>();
        list.add(medicalRecord1);
        list.add(medicalRecord2);

        //Act
        jsonRepresentation.setMedicalrecords(list);

        //Assert
        assertEquals(medicalRecord1, jsonRepresentation.getMedicalrecords().get(0));
        assertEquals(medicalRecord2, jsonRepresentation.getMedicalrecords().get(1));
    }

    @Test
    public void setMedicalrecords_MedicalRecordsNull_MedicalRecordsSet() {
        //Arrange
        MedicalRecord medicalRecord1 = null;
        MedicalRecord medicalRecord2 = null;

        List<MedicalRecord> list = new ArrayList<>();
        list.add(medicalRecord1);
        list.add(medicalRecord2);

        //Act
        jsonRepresentation.setMedicalrecords(list);

        //Assert
        assertEquals(medicalRecord1, jsonRepresentation.getMedicalrecords().get(0));
        assertEquals(medicalRecord2, jsonRepresentation.getMedicalrecords().get(1));
    }

    @Test
    public void setMedicalrecords_MedicalRecordsListEmpty_MedicalRecordsListSet() {
        //Arrange
        List<MedicalRecord> list = new ArrayList<>();

        //Act
        jsonRepresentation.setMedicalrecords(list);

        //Assert
        assertEquals(list, jsonRepresentation.getMedicalrecords());
    }
}
