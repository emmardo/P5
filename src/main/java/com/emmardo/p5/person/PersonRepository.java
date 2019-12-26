package com.emmardo.p5.person;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonRepository {

    private List<Person> persons;

    public PersonRepository() {

        persons = new ArrayList<>();
    }

    public List<Person> findAll() {

        return persons;
    }

    public Person getPerson(String firstName, String lastName){

        Optional<Person> personOptional = persons.stream().filter(p -> p.getFirstName().equals(firstName)
                        && p.getLastName().equals(lastName)).findFirst();

        return personOptional.orElse(null);
    }

    public void createPerson(Person person){

        if(persons.stream().noneMatch(p ->
                p.getFirstName().equals(person.getFirstName())
                        && p.getLastName().equals(person.getLastName()))) {

            persons.add(person);
        }
    }

    public void deletePerson(String firstName, String lastName) {

        if(persons.stream().anyMatch(p ->
                p.getFirstName().equals(firstName)
                        && p.getLastName().equals(lastName))) {

            persons.remove(getPerson(firstName, lastName));
        }
    }
}
