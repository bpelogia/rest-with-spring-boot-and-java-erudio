package br.com.erudio.data.vo.v2;

import br.com.erudio.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "first_name", "last_name", "birthday", "gender","address"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String key;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String address;
    private Gender gender;
    @JsonProperty("birthday")
    private LocalDate birthDay;
    @JsonIgnore
    private Integer updatedTimes = 0;

    public PersonVO() {}
    public PersonVO(
            String key,
            String firstName,
            String lastName,
            String address,
            Gender gender,
            LocalDate birthDay) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.birthDay = birthDay;
    }
}
