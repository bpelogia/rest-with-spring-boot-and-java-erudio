package br.com.erudio.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Data
@Document
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String firstName;
    @Indexed(unique = true)
    private String lastName;
    private String address;
    private Gender gender;
    private Integer updatedTimes = 0;

    public Person(String firstName, String lastName, String address, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }
}
