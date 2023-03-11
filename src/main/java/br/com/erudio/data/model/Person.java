package br.com.erudio.data.model;

import br.com.erudio.data.enumeration.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String firstName;
    //@Indexed(unique = true)
    private String lastName;
    private String address;
    private Gender gender;
    private Integer updatedTimes = 0;

    public Person(){}
    public Person(
            String firstName,
            String lastName,
            String address,
            Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }
}