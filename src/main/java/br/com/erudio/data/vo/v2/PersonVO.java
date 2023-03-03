package br.com.erudio.data.vo.v2;

import br.com.erudio.model.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
public class PersonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String address;
    private Gender gender;
    private LocalDate birthDay;
    @JsonIgnore
    private Integer updatedTimes = 0;

    public PersonVO() {}
    public PersonVO(
            String id,
            String firstName,
            String lastName,
            String address,
            Gender gender,
            LocalDate birthDay) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.birthDay = birthDay;
    }
}
