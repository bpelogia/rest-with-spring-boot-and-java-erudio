package br.com.erudio.controllers;


import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(
            value = "/v1",
            produces = { MediaType.APPLICATION_YAML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }
    )
    public List<PersonVO> fetchAllPersons() {
        return personServices.findAll();
    }

    @GetMapping(
            value = "/v1/{id}",
            produces = { MediaType.APPLICATION_YAML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }
    )
    public PersonVO findPersonById(@PathVariable(value = "id") String id){
        return personServices.findById(id);
    }

    @PostMapping(
            value = "/v1",
            consumes = { MediaType.APPLICATION_YAML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON },
            produces = { MediaType.APPLICATION_YAML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }
    )
    public br.com.erudio.data.vo.v1.PersonVO createPerson(@RequestBody PersonVO person){
        return personServices.create(person);
    }

    @PostMapping(
            value = "/v2",
            consumes = { MediaType.APPLICATION_YAML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON },
            produces = { MediaType.APPLICATION_YAML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }
    )
    public br.com.erudio.data.vo.v2.PersonVO createPersonV2(@RequestBody br.com.erudio.data.vo.v2.PersonVO person){
        return personServices.createV2(person);
    }

    @PutMapping(
            value = "/v1",
            consumes = { MediaType.APPLICATION_YAML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON },
            produces = { MediaType.APPLICATION_YAML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }
    )
    public PersonVO updatePerson(@RequestBody PersonVO person){
        return personServices.update(person);
    }

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") String id){
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
