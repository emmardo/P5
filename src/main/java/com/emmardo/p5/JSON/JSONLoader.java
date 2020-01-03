package com.emmardo.p5.JSON;

import com.emmardo.p5.firestation.FireStation;
import com.emmardo.p5.firestation.FireStationRepository;
import com.emmardo.p5.medicalrecord.MedicalRecord;
import com.emmardo.p5.medicalrecord.MedicalRecordRepository;
import com.emmardo.p5.person.Person;
import com.emmardo.p5.person.PersonRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Component
public class JSONLoader {

    Logger logger = LoggerFactory.getLogger(JSONLoader.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            File json = ResourceUtils.getFile("classpath:data.json");
            InputStream in = new FileInputStream(json);
            InputStreamReader reader = new InputStreamReader(in);

            Gson parser = new Gson();
            JSONRepresentation representation = parser.fromJson(reader, JSONRepresentation.class);

            for(Person person : representation.getPersons()) {

                personRepository.createPerson(person);
                logger.debug("Persons from DB loaded");
            }

            for (FireStation fireStation: representation.getFirestations()) {

                fireStationRepository.createFireStation(fireStation);
                logger.debug("Fire Stations from DB loaded");
            }

            for (MedicalRecord medicalRecord: representation.getMedicalrecords()) {
                medicalRecordRepository.createMedicalRecord(medicalRecord);
                logger.debug("Medical Records from DB loaded");
            }
        } catch (FileNotFoundException e) {

            logger.error("No DB file exception");

            e.printStackTrace();
        }

    }
}
