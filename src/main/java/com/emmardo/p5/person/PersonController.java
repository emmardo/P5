package com.emmardo.p5.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> getAllPersons() {

        logger.info("GET /persons request made");
        return personService.getAllPersons();
    }

    @GetMapping("/person")
    public Person getPerson(@RequestParam String firstName, @RequestParam String lastName) {

        logger.info("GET /person request made with the parameters " + firstName + " and " + lastName);

        if(personService.getAllPersons().stream().anyMatch(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))) {

            logger.info("GET /person request SUCCESSFUL");
        }else{

            logger.error("GET /person request ERROR");
        }

        return personService.getPerson(firstName, lastName);
    }

    @PostMapping("/person")
    public void createPerson(@RequestBody Person person) {

        logger.info("POST /person request made with a person of details " + person.getFirstName() + ", " + person.getLastName() + ", " + person.getAddress() + ", " + person.getCity() + ", " + person.getZip() + ", " + person.getPhone() + " and " + person.getEmail());

        if(personService.getAllPersons().stream().noneMatch(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))) {

            logger.info("POST /person request SUCCESSFUL");
        }else{

            logger.error("POST /person request ERROR");
        }

       personService.createPerson(person);
    }

    @PutMapping("/person")
    public void updatePerson(@RequestBody Person person) {

        logger.info("PUT /person request made with a person of details " + person.getFirstName() + ", " + person.getLastName() + ", " + person.getAddress() + ", " + person.getCity() + ", " + person.getZip() + ", " + person.getPhone() + " and " + person.getEmail());

        if(personService.getAllPersons().stream().anyMatch(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))) {

            logger.info("PUT /person request SUCCESSFUL");
        }else{

            logger.error("PUT /person request ERROR");
        }

        personService.updatePerson(person);

    }

    @DeleteMapping("/person")
    public void deleteAPerson(@RequestParam String firstName, @RequestParam String lastName) {

        logger.info("DELETE /person request made with the parameters " + firstName + " and " + lastName);

        if(personService.getAllPersons().stream().anyMatch(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))) {

            logger.info("DELETE /person request SUCCESSFUL");
        }else{

            logger.error("DELETE /person request ERROR");
        }

        personService.deletePerson(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> getEmailsFromPersonsInCity(@RequestParam String city) {

        logger.info("GET /communityEmail request made with the parameter " + city);

        if(personService.getAllPersons().stream().anyMatch(p -> p.getCity().equals(city))) {

            logger.info("GET /communityEmail request SUCCESSFUL");
        }else{

            logger.error("GET /communityEmail request ERROR");
        }

        return personService.getEmailsByCity(city);
    }
}
