package com.emmardo.p5.JSON;

import com.emmardo.p5.firestation.FireStation;
import com.emmardo.p5.medicalrecord.MedicalRecord;
import com.emmardo.p5.person.Person;

import java.util.List;
import java.util.Map;

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


    /*String string1;
    String string2;*//*
    //Maps
    private Map<String, String> strings;

    public Map<String, String> getStrings() {
        return strings;
    }

    public void setStrings(Map<String, String> strings) {
        this.strings = strings;
    }

    //List of maps
    private List<Map> maps;

    public List<Map> getMaps() {return maps;}

    public void setMaps(List<Map> maps) {this.maps = maps; }*/
}
