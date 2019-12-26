package com.emmardo.p5.medicalrecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        logger.info("getAllMedicalRecords() called");
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/MedicalRecord")
    public MedicalRecord getMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {

        if(medicalRecordService.getAllMedicalRecords().stream().anyMatch(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))) {

            logger.info("getMedicalRecord() called");
        }else{

            logger.error("getMedicalRecord() ERROR");
        }

        return medicalRecordService.getMedicalRecord(firstName, lastName);
    }

    @PostMapping("/MedicalRecord")
    public void createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        if(medicalRecordService.getAllMedicalRecords().stream().noneMatch(mr -> mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName()))) {

            logger.info("createMedicalRecord() called");
        }else{

            logger.error("createMedicalRecord() ERROR");
        }

        medicalRecordService.createMedicalRecord(medicalRecord);
    }

    @PutMapping("/MedicalRecord")
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        if(medicalRecordService.getAllMedicalRecords().stream().anyMatch(mr -> mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName()))) {

            logger.info("updateMedicalRecord() called");
        }else{

            logger.error("updateMedicalRecord() ERROR");
        }

        medicalRecordService.updateMedicalRecord(medicalRecord);
    }

    @DeleteMapping("/MedicalRecord")
    public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {

        if(medicalRecordService.getAllMedicalRecords().stream().anyMatch(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))) {

            logger.info("updateMedicalRecord() called");
        }else{

            logger.error("updateMedicalRecord() ERROR");
        }

        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
