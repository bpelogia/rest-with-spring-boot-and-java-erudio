package br.com.erudio.services;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.mapper.DozerMapper;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.framework.exceptions.RequiredObjectIsNullException;
import br.com.erudio.framework.exceptions.ResourceNotFoundException;
import br.com.erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    private final Logger logger = Logger.getLogger(BookServices.class.getName());
    @Autowired
    BookRepository repository;

    public List<BookVO> findAll() {
        logger.info("##########-> FINDING ALL BOOKS <-##########");
        var persons =  DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        persons
                .forEach(p-> p.add(linkTo(methodOn(BookController.class).findBookById(p.getKey())).withSelfRel()));
        return persons;
    }

    public BookVO findById(String id) {
        logger.info("##########-> FINDING ONE BOOK <-##########");
        var entity =  repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
       /*adequando aplicação ao HATEOS (api RestFull)-> incluindo links de navegação*/
        vo.add(linkTo(methodOn(BookController.class).findBookById(id)).withSelfRel());

        return vo;
    }

    public BookVO create(BookVO vo) {
        if(vo == null) throw new RequiredObjectIsNullException();

        logger.info("##########-> CREATING ONE BOOK <-##########");
        var entity = DozerMapper.parseObject(vo, Book.class);

        BookVO voInserted = DozerMapper.parseObject(repository.insert(entity), BookVO.class);
        /*adequando aplicação ao HATEOS (api RestFull)-> incluindo links de navegação*/
        voInserted.add(linkTo(methodOn(BookController.class).findBookById(voInserted.getKey())).withSelfRel());

        return voInserted;
    }

    public BookVO update(BookVO vo) {
        if(vo == null) throw new RequiredObjectIsNullException();
        logger.info("##########-> UPDATING ONE BOOK <-##########");

        var entity = repository.findById(vo.getKey()).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        entity.setAuthor(vo.getAuthor());
        entity.setTitle(vo.getTitle());
        entity.setPrice(vo.getPrice());
        entity.setLaunchDate(vo.getLaunchDate());
        entity.setUpdatedTimes(entity.getUpdatedTimes()+1);

        BookVO voInserted = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        /* adequando aplicação ao HATEOS (api RestFull)-> incluindo links de navegação */
        voInserted.add(linkTo(methodOn(BookController.class).findBookById(voInserted.getKey())).withSelfRel());

        return voInserted;
    }

    public void delete(String id) {
        logger.info("##########-> DELETING ONE BOOK <-##########");
        var entity = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No records found for this ID!")
        );
        repository.delete(entity);
    }
}
