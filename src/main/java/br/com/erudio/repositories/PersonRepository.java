package br.com.erudio.repositories;

import br.com.erudio.data.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {

    Optional<Person> findPersonByFirstName(String firstName);

}