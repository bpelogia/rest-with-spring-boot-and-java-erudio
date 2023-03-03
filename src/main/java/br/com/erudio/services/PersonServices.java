package br.com.erudio.services;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        var persons =  DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons
                .forEach(p-> p.add(linkTo(methodOn(PersonController.class).findPersonById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(String id) {
        logger.info("finding one person");
        var entity =  personRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
       /*adequando aplicação ao HATEOS (api RestFull)-> incluindo links de navegação*/
        vo.add(linkTo(methodOn(PersonController.class).findPersonById(id)).withSelfRel());

        return vo;
    }

    public PersonVO create(PersonVO person) {
        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);

        PersonVO vo = DozerMapper.parseObject(personRepository.insert(entity), PersonVO.class);
        /*adequando aplicação ao HATEOS (api RestFull)-> incluindo links de navegação*/
        vo.add(linkTo(methodOn(PersonController.class).findPersonById(vo.getKey())).withSelfRel());

        return vo;
    }

    public br.com.erudio.data.vo.v2.PersonVO createV2(br.com.erudio.data.vo.v2.PersonVO person) {
        logger.info("creating one person");
        var entity = mapper.convertVoToEntity(person);

        br.com.erudio.data.vo.v2.PersonVO vo = mapper.convertEntityToVo(personRepository.insert(entity));
        /*adequando aplicação ao HATEOS (api RestFull)-> incluindo links de navegação*/
        vo.add(linkTo(methodOn(PersonController.class).findPersonById(vo.getKey())).withSelfRel());
        return vo;

    }

    public PersonVO update(PersonVO person) {
        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("updating one person");
        var entity = personRepository.findById(person.getKey()).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setUpdatedTimes(entity.getUpdatedTimes()+1);

        PersonVO vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        /*adequando aplicação ao HATEOS (api RestFull)-> incluindo links de navegação*/
        vo.add(linkTo(methodOn(PersonController.class).findPersonById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(String id) {
        logger.info("deleting one person");
        var entity = personRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        personRepository.delete(entity);
    }
}
