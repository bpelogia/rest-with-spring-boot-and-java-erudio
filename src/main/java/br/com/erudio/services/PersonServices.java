package br.com.erudio.services;

import br.com.erudio.data.vo.v2.PersonVO2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Gender;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {
        logger.info("finding all people");
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(String id) {
        logger.info("finding one person");
        var entity =  personRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(personRepository.insert(entity), PersonVO.class);
    }

    public PersonVO2 createV2(PersonVO2 person) {
        logger.info("creating one person");
        var entity = mapper.convertVoToEntity(person);
        return mapper.convertEntityToVo(personRepository.insert(entity));

    }

    public PersonVO update(PersonVO person) {
        logger.info("updating one person");
        var entity = personRepository.findById(person.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setUpdatedTimes(entity.getUpdatedTimes()+1);
        return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
    }

    public void delete(String id) {
        logger.info("deleting one person");
        var entity = personRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        personRepository.delete(entity);
    }
}
