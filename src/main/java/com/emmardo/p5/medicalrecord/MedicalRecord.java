package com.emmardo.p5.medicalrecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public MedicalRecord() {
        this.firstName = "";
        this.lastName = "";
        this.birthdate = "";
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }

    public MedicalRecord(String firstName, String lastName,
            String birthdate, List<String> medications,
            List<String> allergies) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() { return birthdate; }

    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public List<String> getMedications() { return medications; }

    public void setMedications(List<String> medicationsList) {

        medications.clear();

        medications.addAll(medicationsList); }


    public String getMedication(String medication) {
        Optional<String> element = getMedications().stream().filter(medicationInstance ->
                medicationInstance.equals(medication)).findFirst();

        return element.orElse(null);
    }

    public void setMedication(String medication){ medications.add(medication); }

    public List<String> getAllergies() { return allergies; }

    public void setAllergies(List<String> allergiesList){

        allergies.clear();
        allergies.addAll(allergiesList); }


    public String getAllergy(String allergy) {
        Optional<String> element = getAllergies().stream().filter(allergyInstance ->
                allergyInstance.equals(allergy)).findFirst();

        return element.orElse(null);
    }

    public void setAllergy(String allergy) { allergies.add(allergy); }

}
