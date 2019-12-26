package com.emmardo.p5.firestation;

import com.emmardo.p5.medicalrecord.MedicalRecordService;
import com.emmardo.p5.person.Person;
import com.emmardo.p5.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private PersonService personService;

    public FireStationService (FireStationRepository fireStationRepository, MedicalRecordService medicalRecordService, PersonService personService){

        this.fireStationRepository = fireStationRepository;
        this.medicalRecordService = medicalRecordService;
        this.personService = personService;
    }

    public List<FireStation> getAllFireStations() {
        return fireStationRepository.findAll();
    }

    public FireStation getFireStation(String address) {

        FireStation fireStation;

        if(fireStationRepository.findAll().stream().anyMatch(fs -> fs.getAddress()
                .equals(address))){

            fireStation = fireStationRepository.findAll().stream().filter(fs -> fs.getAddress().equals(address)).findFirst().get();
        }else{

            fireStation = null;
        }

        return fireStation;
    }

    public void createFireStation(FireStation fireStation) {

        if (fireStationRepository.findAll().stream().noneMatch(fs -> fs.getAddress().equals(fireStation.getAddress()))) {

            fireStationRepository.createFireStation(fireStation);
        }
    }

    public void updateStation(FireStation fireStation) {

        if (fireStationRepository.findAll().stream().anyMatch(fs -> fs.getAddress().equals(fireStation.getAddress()))) {

            fireStationRepository.getFireStation(fireStation.getAddress()).setStation(fireStation.getStation());
        }
    }

    public void deleteStation(String address) {

        if (fireStationRepository.findAll().stream().filter(fs -> fs.getAddress().equals(address)).findFirst().isPresent() == true) {

            fireStationRepository.deleteFireStation(address);
        }
    }

    public List<String> getAddressesCoveredByFireStation(String station) {

        List<String> addresses = new ArrayList<>();

        List<FireStation> filteredFireStations = fireStationRepository.findAll().stream().filter(fs -> fs.getStation().equals(station)).collect(Collectors.toList());

        for(FireStation fireStation: filteredFireStations) {

            if (addresses.stream().noneMatch(a -> a.equals(fireStation.getAddress()))) {

                addresses.add(fireStation.getAddress());
            }
        }

        return addresses;
    }

    public List<Person> getPersonsByStation(String station) {

        List<String> addresses = new ArrayList<>();

        List<Person> persons = new ArrayList<>();

        if(fireStationRepository.findAll().stream().anyMatch(fs -> fs.getStation().equals(station))) {

            addresses = getAddressesCoveredByFireStation(station);

            persons = personService.getPersonsLivingAtAddresses(addresses);
        }

        return persons;
    }

    public List<Person> getAdultsAndChildrenServicedByFireStation(String station) {

        List<Person> result = new ArrayList<>();

        if(fireStationRepository.findAll().stream().anyMatch(fs -> fs.getStation().equals(station))) {

            for (Person person : getPersonsByStation(station)) {

                Person p = new Person();

                p.setFirstName(person.getFirstName());
                p.setLastName(person.getLastName());
                p.setAddress(person.getAddress());
                p.setPhone(person.getPhone());

                result.add(p);
            }

            //The following Persons were created to implement a requirement only
            //and have unusual characteristics
            Person numberOfAdults = new Person();
            Person numberOfChildren = new Person();

            numberOfAdults.setNumberOfAdults(personService.getNumberOfAdultsInList(getPersonsByStation(station)));
            numberOfChildren.setNumberOfChildren(personService.getNumberOfChildrenInList(getPersonsByStation(station)));

            result.add(numberOfAdults);
            result.add(numberOfChildren);
        }

        return result;
    }

    public List<String> getPhonesByStation(String fireStation) {

        List<String> phones = new ArrayList<>();

        if(fireStationRepository.findAll().stream().anyMatch(fs -> fs.getStation().equals(fireStation))) {

            for (Person person : getPersonsByStation(fireStation)) {

                phones.add(person.getPhone());
            }
        }

        return phones;
    }

    public HashMap<String, List<Person>> getFireStationAndPersonsByAddress(String address) {

        HashMap<String, List<Person>> result = new HashMap<>();

        if(fireStationRepository.findAll().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            List<Person> persons = new ArrayList<>();

            for (Person person : getPersonsByStation(getFireStation(address).getStation())) {

                Person p = new Person();

                p.setFirstName(person.getFirstName());
                p.setLastName(person.getLastName());
                p.setPhone(person.getPhone());
                p.setAge(personService.getPersonsAge(person));
                p.setMedications(medicalRecordService.getMedicalRecord(person.getFirstName(), person.getLastName()).getMedications());
                p.setAllergies(medicalRecordService.getMedicalRecord(person.getFirstName(), person.getLastName()).getAllergies());

                persons.add(p);
            }

            FireStation fireStation = new FireStation();

            fireStation.setStation(getFireStation(address).getStation());

            result.put(fireStation.getStation(), persons);
        }

        return result;
    }

    public HashMap<String, List<Person>> getHouseholdsCoveredByStations(List<String> stations) {

        HashMap<String, List<Person>> result = new HashMap<>();

        for(String station: stations) {

            if(fireStationRepository.findAll().stream().anyMatch(fs -> fs.getStation().equals(station))) {

                List<Person> persons = new ArrayList<>();

                for (String stationNumber: stations) {

                    List<String> addressesList = new ArrayList<>();
                    addressesList = getAddressesCoveredByFireStation(stationNumber);

                    for (String address : addressesList) {

                        List<Person> personsList = new ArrayList<>();
                        personsList = personService.getPersonsLivingAtAddresses(Arrays.asList(address));

                        for (Person person : personsList) {

                            Person p = new Person();

                            p.setFirstName(person.getFirstName());
                            p.setLastName(person.getLastName());
                            p.setPhone(person.getPhone());
                            p.setAge(personService.getPersonsAge(person));
                            p.setMedications(medicalRecordService.getMedicalRecord(person.getFirstName(), person.getLastName()).getMedications());
                            p.setAllergies(medicalRecordService.getMedicalRecord(person.getFirstName(), person.getLastName()).getAllergies());

                            persons.add(p);
                        }

                        result.put("FireStation: "+ stationNumber + ". " + "Address: " + address, persons);
                    }
                }
            }
        }

        return result;
    }

    public Person getPersonsInformation(String firstName, String lastName) {

        Person person = new Person();

        if(personService.getAllPersons().stream().anyMatch(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))) {

            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setAddress(personService.getPerson(firstName, lastName).getAddress());
            person.setAge(personService.getPersonsAge(personService.getPerson(firstName, lastName)));
            person.setEmail(personService.getPerson(firstName, lastName).getEmail());
            person.setMedications(medicalRecordService.getMedicalRecord(firstName, lastName).getMedications());
            person.setAllergies(medicalRecordService.getMedicalRecord(firstName, lastName).getAllergies());
        }

        return person;
    }

    public List<Person> getChildrenLivingAtAddressAsWellAsAnyAdult(String address) {

        List<Person> result = new ArrayList<>();

        if(fireStationRepository.findAll().stream().anyMatch(fs -> fs.getAddress().equals(address))) {

            List<Person> peopleAtAddress = personService.getPersonsLivingAtAddresses(new ArrayList<>(Arrays.asList(address)));

            List<Person> adults = new ArrayList<>();

            List<Person> children = new ArrayList<>();

            for (Person person : peopleAtAddress) {

                Person p = new Person();

                p.setFirstName(person.getFirstName());
                p.setLastName(person.getLastName());
                p.setAge(personService.getPersonsAge(person));

                int age = Integer.parseInt(personService.getPersonsAge(person));

                if (age >= 18) {

                    adults.add(p);
                } else {

                    children.add(p);
                }
            }

            if (!children.isEmpty()) {

                result.addAll(children);
                result.addAll(adults);
            }
        }

        return result;
    }
}
