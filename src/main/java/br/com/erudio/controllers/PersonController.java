package br.com.erudio.controllers;


import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = {"http://localhost:8080"})
@RestController()
@RequestMapping("/api/person")
@Tag(name= "People", description = "Endpoints for managing People")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(
            value = "/v1",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    @Operation(summary = "Finds all People", description = "Finds all People",
            tags = {"People"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                        )
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public List<PersonVO> fetchAllPersons() {
        return personServices.findAll();
    }

    @GetMapping(
            value = "/v1/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    @Operation(summary = "Finds a Person", description = "Finds a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public PersonVO findPersonById(@PathVariable(value = "id") String id){
        return personServices.findById(id);
    }

    @PostMapping(
            value = "/v1",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML or YAML of person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public PersonVO createPerson(@RequestBody PersonVO person){
        return personServices.create(person);
    }

    @PostMapping(
            value = "/v2",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    public br.com.erudio.data.vo.v2.PersonVO createPersonV2(@RequestBody br.com.erudio.data.vo.v2.PersonVO person){
        return personServices.createV2(person);
    }

    @PutMapping(
            value = "/v1",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    @Operation(summary = "Updates a new Person",
            description = "Update a Person by passing in a JSON, XML or YAML of person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public PersonVO updatePerson(@RequestBody PersonVO person){
        return personServices.update(person);
    }

    @DeleteMapping(value = "/v1/{id}")
    @Operation(summary = "Delete a Person",
            description = "Delete a Person by passing in a JSON, XML or YAML of person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") String id){
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
