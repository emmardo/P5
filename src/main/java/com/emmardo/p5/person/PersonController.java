package com.emmardo.p5.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> getAllPersons() {

        logger.info("getAllPersons() called");
        return personService.getAllPersons();
    }

    @GetMapping("/person")
    public Person getPerson(@RequestParam String firstName, @RequestParam String lastName) {

        if(personService.getAllPersons().stream().anyMatch(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))) {

            logger.info("getPerson() called");
        }else{

            logger.error("getPerson() ERROR");
        }

        return personService.getPerson(firstName, lastName);
    }

    @PostMapping("/person")
    public void createPerson(@RequestBody Person person) {

        if(personService.getAllPersons().stream().noneMatch(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))) {

            logger.info("createPerson() called");
        }else{

            logger.error("createPerson() ERROR");
        }

       personService.createPerson(person);
    }

    @PutMapping("/person")
    public void updatePerson(@RequestBody Person person) {

        if(personService.getAllPersons().stream().anyMatch(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))) {

            logger.info("updatePerson() called");
        }else{

            logger.error("updatePerson() ERROR");
        }

        personService.updatePerson(person);

    }

    @DeleteMapping("/person")
    public void deleteAPerson(@RequestParam String firstName, @RequestParam String lastName) {

        if(personService.getAllPersons().stream().anyMatch(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))) {

            logger.info("deleteAPerson() called");
        }else{

            logger.error("deleteAPerson() ERROR");
        }

        personService.deletePerson(firstName, lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> getEmailsFromPersonsInCity(@RequestParam String city) {

        if(personService.getAllPersons().stream().anyMatch(p -> p.getCity().equals(city))) {

            logger.info("/communityEmail called");
        }else{

            logger.error("/communityEmail ERROR");
        }

        return personService.getEmailsByCity(city);
    }
}
