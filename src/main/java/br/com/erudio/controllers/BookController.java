package br.com.erudio.controllers;


import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookServices;
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

@RestController()
@RequestMapping("/api/book")
@Tag(name= "Book", description = "Endpoints for managing People")
public class BookController {

    @Autowired
    private BookServices services;

    @GetMapping(
            value = "/v1",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    @Operation(summary = "Finds all Books", description = "Finds all Books",
            tags = {"Book"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
                        )
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public List<BookVO> fetchAllBooks() {
        return services.findAll();
    }

    @GetMapping(
            value = "/v1/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    @Operation(summary = "Finds a Book", description = "Finds a Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public BookVO findBookById(@PathVariable(value = "id") String id){
        return services.findById(id);
    }

    @PostMapping(
            value = "/v1",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    @Operation(summary = "Add a new Book",
            description = "Add a new Book by passing in a JSON, XML or YAML of person",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public BookVO createBook(@RequestBody BookVO vo){
        return services.create(vo);
    }

    @PutMapping(
            value = "/v1",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    @Operation(summary = "Updates a Book",
            description = "Update a Book by passing in a JSON, XML or YAML of person",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public BookVO updatePerson(@RequestBody BookVO person){
        return services.update(person);
    }

    @DeleteMapping(value = "/v1/{id}")
    @Operation(summary = "Delete a Book",
            description = "Delete a Book by passing in a JSON, XML or YAML of person",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") String id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}
