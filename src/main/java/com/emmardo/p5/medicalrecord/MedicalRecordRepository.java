package com.emmardo.p5.medicalrecord;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecords;

    public MedicalRecordRepository() {

        medicalRecords = new ArrayList<>();
    }

    public List<MedicalRecord> findAll() {

        return medicalRecords.stream().collect(Collectors.toList());
    }

    public MedicalRecord getMedicalRecord(String firstName, String lastName){

        Optional<MedicalRecord> medicalRecordOptional = medicalRecords.stream().filter(

                p -> p.getFirstName().equals(firstName)
                        && p.getLastName().equals(lastName)).findFirst();

        return medicalRecordOptional.orElse(null);
    }

    public void createMedicalRecord(MedicalRecord medicalRecord){

        if(medicalRecords.stream().noneMatch(m -> m.getFirstName().equals(medicalRecord.getFirstName())
                && m.getLastName().equals(medicalRecord.getLastName()))) {

            medicalRecords.add(medicalRecord);
        }
    }

    public void deleteMedicalRecord(String firstName, String lastName) {

        if(medicalRecords.stream().anyMatch(mr -> mr.getFirstName().equals(firstName)
                && mr.getLastName().equals(lastName))) {

            medicalRecords.remove(getMedicalRecord(firstName,lastName));
        }
    }
}
