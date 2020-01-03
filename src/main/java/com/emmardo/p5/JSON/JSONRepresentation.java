package com.emmardo.p5.JSON;

import com.emmardo.p5.firestation.FireStation;
import com.emmardo.p5.medicalrecord.MedicalRecord;
import com.emmardo.p5.person.Person;

import java.util.List;

public class JSONRepresentation {

    //List of persons
    private List<Person> persons;

    //List of firestations
    private List<FireStation> firestations;

    //List of medicalrecords
    private List<MedicalRecord> medicalrecords;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<FireStation> getFirestations() { return firestations; }

    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecord> getMedicalrecords() { return medicalrecords; }

    public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
