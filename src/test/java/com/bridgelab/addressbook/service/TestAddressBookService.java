package com.bridgelab.addressbook.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bridgelab.addressbook.exception.DatabaseReadException;
import com.bridgelab.addressbook.model.Person;
import com.bridgelab.addressbook.repository.PersonRepository;
@SpringBootTest
public class TestAddressBookService {

	@Mock
	private PersonRepository personRepository;
	
	@InjectMocks
	private PersonService personService;
	
	Person person =new Person();
	Person person1 =new Person();
	Person person2 =new Person();
	Person person3 =new Person();
	
	@BeforeEach
	public void setup() {
		
		person.setId(1);
		person.setName("Pooja");
		person.setEmail("pooja.sd@gmail.com");
		person.setCity("pune");
		person.setMobileNo("9011220070");
		
		person1.setId(2);
		person1.setName("Abhi");
		person1.setEmail("abhi754@gmail.com");
		person1.setCity("mumbai");
		person1.setMobileNo("9896122057");
		
		person2.setId(3);
		person2.setName("Soni");
		person2.setEmail("soni.111@gmail.com");
		person2.setCity("mumbai");
		person2.setMobileNo("9858805504");
		
		person3.setId(2);
		person3.setName("Soni");
		person3.setEmail("soni.111@gmail.com");
		person3.setCity("Pune");
		person3.setMobileNo("87935143338");
	}

	@Test
    void getAllPersonDataTest() {
        doReturn(Arrays.asList(person,person1)).when(personRepository).findAll();
        
        List<Person> returnedValues = personService.getAllPerson();
        Assertions.assertEquals(returnedValues,Arrays.asList(person,person1), "The sizes should be the same");
       
    }
	
	@Test
	 void getAllPersonDataTestException(){
		try {
			doReturn(Arrays.asList(person,person1)).when(personRepository).findAll();
		}
		catch(NullPointerException e) {
			System.out.println("Null Pointer Exception caught - Database Empty");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Array Index is Out of BoundsS");
		}
		
		
	}
	
	@Test
    void PersonSizetest() {
        doReturn(Arrays.asList(person,person1)).when(personRepository).findAll();
        List<Person> returnedValues = personService.getAllPerson();
        Assertions.assertEquals(returnedValues.size(), 2, "The sizes should be the same");
    }
	
	@Test
    void getPersonByIDTest() {
        doReturn(Optional.of(person1)).when(personRepository).findById(2);
        Person returnedEntity = personService.getPersonById(2);

        Assertions.assertTrue(returnedEntity != null, "Record was not found");
        Assertions.assertEquals(returnedEntity, person1, " REcords should be the same");
    }
	
	@Test
	public void savePersonTest() {
		when(personRepository.save(person2)).thenReturn(person2);
		Person savedPerson = personService.savePerson(person2);
		assertThat(person2).isEqualTo(savedPerson);
	}
	
	@Test
	public void updatePersonByIdTest() {
		when(personRepository.save(person3)).thenReturn(person3);
		doReturn(Optional.of(person1)).when(personRepository).findById(2);
		Person updatePerson = personService.updatePerson(2,person3);
		assertThat(person3).isEqualTo(updatePerson);
	}

	@Test
	public void deletePersonByIdTest() {
		when(personRepository.existsById(1)).thenReturn(true);
		assertThat(true).isEqualTo(personRepository.existsById(1));
		
	}
	
	@Test
    public void getPersonByNameTest() {
		
        doReturn(Optional.of(person)).when(personRepository).findByName("Pooja");
		Person returnedEntity = (Person) personService.getPersonByName("Pooja");
        Assertions.assertTrue(returnedEntity != null, "Record was not found");
        Assertions.assertEquals(returnedEntity, person, " REcords should be the same");
    }
}
