package com.emmardo.p5.medicalrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService (MedicalRecordRepository medicalRecordRepository){

        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getAllMedicalRecords() {

        return medicalRecordRepository.findAll();
    }

    public MedicalRecord getMedicalRecord(String firstName, String lastName) {

        MedicalRecord medicalRecord = new MedicalRecord();

        if(medicalRecordRepository.findAll().stream().anyMatch(mr -> mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName))) {

            medicalRecord = medicalRecordRepository.findAll().stream().filter(m -> m.getFirstName().equals(firstName)
                    && m.getLastName().equals(lastName)).findFirst().get();
        }

        return medicalRecord;
    }

    public void createMedicalRecord(MedicalRecord medicalRecord) {

        if(medicalRecordRepository.findAll().stream().noneMatch(p ->
                p.getFirstName().equals(medicalRecord.getFirstName())
                        && p.getLastName().equals(medicalRecord.getLastName()))) {

            medicalRecordRepository.createMedicalRecord(medicalRecord);
        }
    }

    public void updateMedicalRecord(MedicalRecord medicalRecord) {

        if(medicalRecordRepository.findAll().stream().anyMatch(p -> p.getFirstName().equals(medicalRecord.getFirstName())
                && p.getLastName().equals(medicalRecord.getLastName()))) {

            MedicalRecord filtered = medicalRecordRepository.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());

            filtered.setBirthdate(medicalRecord.getBirthdate());
            filtered.setMedications(medicalRecord.getMedications());
            filtered.setAllergies(medicalRecord.getAllergies());
        }
    }

    public void deleteMedicalRecord(String firstName, String lastName) {

        if(medicalRecordRepository.findAll().stream().anyMatch(mr ->
                mr.getFirstName().equals(firstName)
                        && mr.getLastName().equals(lastName))) {

            medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
        }
    }
}
