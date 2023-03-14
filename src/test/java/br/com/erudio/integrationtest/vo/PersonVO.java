package br.com.erudio.integrationtest.vo;

import br.com.erudio.data.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@XmlRootElement
public class PersonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
    private Integer updatedTimes = 0;

    public PersonVO() {}
    public PersonVO(
            String id,
            String firstName,
            String lastName,
            String address,
            Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }
}
