package br.com.erudio.controllers;


import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> fetchAllPersons() {
        return personServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPersonById(@PathVariable(value = "id") String id){
        return personServices.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person createPerson(@RequestBody Person person){
        return personServices.create(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person updatePerson(@RequestBody Person person){
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") String id){
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
