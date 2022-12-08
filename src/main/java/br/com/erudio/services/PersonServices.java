package br.com.erudio.services;

import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private static final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {
        logger.info("finding all people");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Bruno");
        person.setLastName("Henrique");
        person.setAddress("São Paulo - SP - Brasil");
        person.setGender("Male");
        return person;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name"+i);
        person.setLastName("Last name"+i);
        person.setAddress("Address in Brasil"+i);
        person.setGender("Male");
        return person;
    }

    public Person create(Person person) {
        logger.info("creating one person");

        return person;
    }

    public Person update(Person person) {
        logger.info("updating one person");

        return person;
    }

    public void delete(String id) {
        logger.info("deleting one person");

    }
}
