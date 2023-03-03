package br.com.erudio.data.vo.v1;

import br.com.erudio.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

@Data
@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "address"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private String key;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
    private Integer updatedTimes = 0;

    public PersonVO() {}
    public PersonVO(
            String key,
            String firstName,
            String lastName,
            String address,
            Gender gender) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }
}
