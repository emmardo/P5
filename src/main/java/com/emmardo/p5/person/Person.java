package com.emmardo.p5.person;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;
    private String age;
    private List<String> medications;
    private List<String> allergies;

    //These last 2 variables exist ONLY to implement a requirement
    private String numberOfAdults;
    private String numberOfChildren;

    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.city = "";
        this.zip = "";
        this.phone = "";
        this.email = "";
        this.age = "";
        this.medications = null;
        this.allergies = null;
        this.numberOfAdults = "";
        this.numberOfChildren = "";
    }

    public Person(String firstName, String lastName, String address,
                  String city, String zip, String phone, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }

    public String getZip() {
        return zip;
    }
    public void setZip(String zip){
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getAge() { return age; }
    public void setAge(String age) {this.age = age; }

    public List<String> getMedications() { return medications; }
    public void setMedications(List<String> medications) { this.medications = medications; }

    public List<String> getAllergies() { return allergies; }
    public void setAllergies(List<String> allergies) { this.allergies = allergies; }

    public String getNumberOfAdults() { return numberOfAdults; }
    public void setNumberOfAdults(String numberOfAdults) { this.numberOfAdults = numberOfAdults; }

    public String getNumberOfChildren() { return numberOfChildren; }
    public void setNumberOfChildren(String numberOfChildren) { this.numberOfChildren = numberOfChildren; }
}
