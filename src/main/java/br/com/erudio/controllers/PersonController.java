package br.com.erudio.controllers;


import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> fetchAllStudents() {
        return personServices.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPersonById(@PathVariable(value = "id") String id){
        return personServices.findById(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person createPerson(@RequestBody Person person){
        return personServices.create(person);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person updatePerson(@RequestBody Person person){
        return personServices.update(person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable(value = "id") String id){
        personServices.delete(id);
    }

}
