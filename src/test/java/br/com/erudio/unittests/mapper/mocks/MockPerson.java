package br.com.erudio.unittests.mapper.mocks;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Gender;
import br.com.erudio.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonVO mockVO() {
        return mockVO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Integer number) {
        return new Person(
                number.toString(),
                "First Name Test" + number,
                "Last Name Test" + number,
                "Addres Test" + number,
                (number % 2)==0 ? Gender.MALE : Gender.FEMALE
        );
    }

    public PersonVO mockVO(Integer number) {
        return new PersonVO(
                number.toString(),
                "First Name Test" + number,
                "Last Name Test" + number,
                "Addres Test" + number,
                (number % 2)==0 ? Gender.MALE : Gender.FEMALE
        );
    }

}
