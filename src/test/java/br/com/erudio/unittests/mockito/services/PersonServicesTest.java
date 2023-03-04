package br.com.erudio.unittests.mockito.services;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.framework.exceptions.RequiredObjectIsNullException;
import br.com.erudio.data.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonServices;
import br.com.erudio.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Person> entity = input.mockEntityList();

        when(repository.findAll()).thenReturn(entity);
        var result = service.findAll();
        assertNotNull(result);
        assertNotNull(result.get(0).getKey());
        assertNotNull(result.get(0).getLinks());
        assertTrue(result.get(0).getLinks().toString().contains("</api/person/v1/0>;rel=\"self\""));
        assertEquals("Addres Test0", result.get(0).getAddress());
        assertEquals("First Name Test3", result.get(3).getFirstName());
        assertEquals("Last Name Test5", result.get(5).getLastName());
        assertEquals("MALE", result.get(0).getGender().toString());
    }

    @Test
    void testFindById() {
        Person entity = input.mockEntity(1);
        entity.setId("00b34a");

        when(repository.findById("00b34a")).thenReturn(Optional.of(entity));
        var result = service.findById("00b34a");
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/person/v1/00b34a>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("FEMALE", result.getGender().toString());
    }

    @Test
    void testCreate() {
        Person entity = input.mockEntity(1);

        Person persisted = entity;
        persisted.setId("00b34a");

        PersonVO vo = input.mockVO(1);
        vo.setKey("00b34a");

        when(repository.insert(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/person/v1/00b34a>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("FEMALE", result.getGender().toString());
    }
    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testeUpdate() {
        Person entity = input.mockEntity(1);
        entity.setId("00b34a");

        Person persisted = entity;
        persisted.setId("00b34a");

        PersonVO vo = input.mockVO(1);
        vo.setKey("00b34a");

        when(repository.findById("00b34a")).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/person/v1/00b34a>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("FEMALE", result.getGender().toString());
    }

    @Test
    void testDelete() {
        Person entity = input.mockEntity(1);
        entity.setId("00b34a");

        when(repository.findById("00b34a")).thenReturn(Optional.of(entity));
        service.delete("00b34a");
    }
}