package br.com.erudio.services;

import br.com.erudio.data.vo.v2.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
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

    public List<br.com.erudio.data.vo.v1.PersonVO> findAll() {
        logger.info("finding all people");
        return DozerMapper.parseListObjects(personRepository.findAll(), br.com.erudio.data.vo.v1.PersonVO.class);
    }

    public br.com.erudio.data.vo.v1.PersonVO findById(String id) {
        logger.info("finding one person");
        var entity =  personRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );

        return DozerMapper.parseObject(entity, br.com.erudio.data.vo.v1.PersonVO.class);
    }

    public br.com.erudio.data.vo.v1.PersonVO create(br.com.erudio.data.vo.v1.PersonVO person) {
        logger.info("creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(personRepository.insert(entity), br.com.erudio.data.vo.v1.PersonVO.class);
    }

    public PersonVO createV2(PersonVO person) {
        logger.info("creating one person");
        var entity = mapper.convertVoToEntity(person);
        return mapper.convertEntityToVo(personRepository.insert(entity));

    }

    public br.com.erudio.data.vo.v1.PersonVO update(br.com.erudio.data.vo.v1.PersonVO person) {
        logger.info("updating one person");
        var entity = personRepository.findById(person.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setUpdatedTimes(entity.getUpdatedTimes()+1);
        return DozerMapper.parseObject(personRepository.save(entity), br.com.erudio.data.vo.v1.PersonVO.class);
    }

    public void delete(String id) {
        logger.info("deleting one person");
        var entity = personRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        personRepository.delete(entity);
    }
}
