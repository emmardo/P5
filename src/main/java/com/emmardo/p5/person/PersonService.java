package com.emmardo.p5.person;

import com.emmardo.p5.medicalrecord.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;

    public PersonService (PersonRepository personRepository, MedicalRecordService medicalRecordService){

        this.personRepository = personRepository;
        this.medicalRecordService = medicalRecordService;
    }

    public List<Person> getAllPersons() {

        return personRepository.findAll();
    }

    public Person getPerson(String firstName, String lastName) {

        Person person = new Person();

        if(personRepository.findAll().stream().anyMatch(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))) {

            person = personRepository.findAll().stream().filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)).findFirst().get();
        }

        return person;
    }

    public void createPerson(Person person) {

        if(personRepository.findAll().stream().noneMatch(p ->
                p.getFirstName().equals(person.getFirstName())
                        && p.getLastName().equals(person.getLastName()))) {

            personRepository.createPerson(person);
        }
    }

    public void updatePerson(Person person) {

        if(personRepository.findAll().stream().anyMatch(p ->
                p.getFirstName().equals(person.getFirstName())
                        && p.getLastName().equals(person.getLastName()))) {

            Person filtered = personRepository.findAll().stream().filter(fp -> fp.getFirstName().equals(person.getFirstName())
                                && fp.getLastName().equals(person.getLastName())).findFirst().get();

            filtered.setAddress(person.getAddress());
            filtered.setCity(person.getCity());
            filtered.setZip(person.getZip());
            filtered.setPhone(person.getPhone());
            filtered.setEmail(person.getEmail());
        }
    }

    public void deletePerson(String firstName, String lastName) {

        if(personRepository.findAll().stream().anyMatch(p ->
                p.getFirstName().equals(firstName)
                        && p.getLastName().equals(lastName))) {

            getAllPersons().remove(getPerson(firstName, lastName));
        }
    }

    public List<Person> getPersonsLivingAtAddresses(List<String> addresses) {

        List<Person> personsList = new ArrayList<>();

        for(String address : addresses){

            personsList.addAll(personRepository.findAll().stream().filter(p -> p.getAddress().equals(address)).collect(Collectors.toList()));
        }

        return personsList;
    }

    public String getPersonsAge(Person person) {

        String age = null;

        if(getAllPersons().stream().anyMatch(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDate today = LocalDate.now();

            LocalDate birthDate = LocalDate.parse(medicalRecordService.getMedicalRecord(person.getFirstName(), person.getLastName()).getBirthdate(), formatter);

            age = String.valueOf(Period.between(birthDate, today).getYears());

            person.setAge(age);
        }

        return age;
    }

    public String getNumberOfAdultsInList(List<Person> persons) {

        int numberOfAdults = 0;

        for(Person person: persons) {

            if(Integer.parseInt(getPersonsAge(person)) >= 18) {
                ++numberOfAdults;
            }
        }

        return String.valueOf(numberOfAdults);
    }

    public String getNumberOfChildrenInList(List<Person> persons) {

        int numberOfChildren = 0;

        for(Person person: persons) {

            if(Integer.parseInt(getPersonsAge(person)) < 18) {
                ++numberOfChildren;
            }
        }

        return String.valueOf(numberOfChildren);
    }

    public List<String> getEmailsByCity(String city) {

        List<String> emails = new ArrayList<>();

        if(personRepository.findAll().stream().anyMatch(p -> p.getCity().equals(city))) {

            List<Person> list = personRepository.findAll().stream().filter(p -> p.getCity().equals(city)).collect(Collectors.toList());

            for (Person person : list) {

                emails.add(person.getEmail());
            }
        }

        return emails;
    }
}
