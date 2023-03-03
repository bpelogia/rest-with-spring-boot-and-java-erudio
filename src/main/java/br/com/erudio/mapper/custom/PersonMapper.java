package br.com.erudio.mapper.custom;

import java.time.LocalDate;

import br.com.erudio.data.vo.v2.PersonVO;
import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service
public class PersonMapper {
	
	public PersonVO convertEntityToVo(Person person) {
		PersonVO vo = new PersonVO();
		vo.setKey(person.getId());
		vo.setAddress(person.getAddress());
		vo.setBirthDay(LocalDate.now());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		return vo;
	}
	
	
	public Person convertVoToEntity(PersonVO person) {
		Person entity = new Person();
		entity.setId(person.getKey());
		entity.setAddress(person.getAddress());
		//vo.setBirthDay(new Date());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		return entity;
	}

}
