package br.com.erudio.controllers;


import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVO2;
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
    public List<PersonVO> fetchAllPersons() {
        return personServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findPersonById(@PathVariable(value = "id") String id){
        return personServices.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonVO createPerson(@RequestBody PersonVO person){
        return personServices.create(person);
    }

    @PostMapping(
            value = "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonVO2 createPersonV2(@RequestBody PersonVO2 person){
        return personServices.createV2(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonVO updatePerson(@RequestBody PersonVO person){
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") String id){
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
