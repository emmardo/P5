package com.emmardo.p5.medicalrecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class MedicalRecordController {

    Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    @Autowired
    private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {

        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/MedicalRecords")
    public List<MedicalRecord> getAllMedicalRecords() {

        logger.info("GET /MedicalRecords request made");
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/MedicalRecord")
    public MedicalRecord getMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {

        logger.info("GET /MedicalRecord request made with the parameters " + firstName + " and " + lastName);

        if(medicalRecordService.getAllMedicalRecords().stream().anyMatch(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))) {

            logger.info("GET /MedicalRecord request SUCCESSFUL");
        }else{

            logger.error("GET /MedicalRecord request ERROR");
        }

        return medicalRecordService.getMedicalRecord(firstName, lastName);
    }

    @PostMapping("/MedicalRecord")
    public void createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        logger.info("POST /MedicalRecord request made with a medicalRecord of details " + medicalRecord.getFirstName() + ", " + medicalRecord.getLastName() + ", " + medicalRecord.getBirthdate() + ", " + Arrays.toString(medicalRecord.getMedications().toArray()) + " and " + Arrays.toString(medicalRecord.getAllergies().toArray()));

        if(medicalRecordService.getAllMedicalRecords().stream().noneMatch(mr -> mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName()))) {

            logger.info("POST /MedicalRecord request SUCCESSFUL");
        }else{

            logger.error("POST /MedicalRecord request ERROR");
        }

        medicalRecordService.createMedicalRecord(medicalRecord);
    }

    @PutMapping("/MedicalRecord")
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        logger.info("PUT /MedicalRecord request made with a medicalRecord of details " + medicalRecord.getFirstName() + ", " + medicalRecord.getLastName() + ", " + medicalRecord.getBirthdate() + ", " + Arrays.toString(medicalRecord.getMedications().toArray()) + " and " + Arrays.toString(medicalRecord.getAllergies().toArray()));

        if(medicalRecordService.getAllMedicalRecords().stream().anyMatch(mr -> mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName()))) {

            logger.info("PUT /MedicalRecord request SUCCESSFUL");
        }else{

            logger.error("PUT /MedicalRecord request ERROR");
        }

        medicalRecordService.updateMedicalRecord(medicalRecord);
    }

    @DeleteMapping("/MedicalRecord")
    public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {

        logger.info("DELETE /MedicalRecord request made with the parameters " + firstName + " and " + lastName);

        if(medicalRecordService.getAllMedicalRecords().stream().anyMatch(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))) {

            logger.info("DELETE /MedicalRecord request SUCCESSFUL");
        }else{

            logger.error("DELETE /MedicalRecord request ERROR");
        }

        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
