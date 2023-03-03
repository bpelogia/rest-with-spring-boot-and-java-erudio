package br.com.erudio.data.vo.v1;

import br.com.erudio.model.Gender;
import lombok.Data;

import java.io.Serializable;

@Data(staticConstructor = "person")
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
