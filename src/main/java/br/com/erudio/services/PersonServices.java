package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Gender;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {
        logger.info("finding all people");
        return personRepository.findAll();
    }

    public Person findById(String id) {
        logger.info("finding one person");
        return personRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
    }

    private Person mockPerson() {
       return new Person(
                "Bruno",
                "Henrique",
                "São Paulo - SP - Brasil",
                Gender.MALE

        );
    }

    public Person create(Person person) {
        logger.info("creating one person");
        return personRepository.insert(person);
    }

    public Person update(Person person) {
        logger.info("updating one person");
        var entity = personRepository.findById(person.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setUpdatedTimes(entity.getUpdatedTimes()+1);
        return personRepository.save(entity);
    }

    public void delete(String id) {
        logger.info("deleting one person");
        var entity = personRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        personRepository.delete(entity);
    }
}
