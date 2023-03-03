package br.com.erudio.data.vo.v2;

import br.com.erudio.model.Gender;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data(staticConstructor = "person")
public class PersonVO2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
    private LocalDate birthDay;
    private Integer updatedTimes = 0;

    public PersonVO2() {}
    public PersonVO2(
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
