package com.bridgelab.addressbook.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.addressbook.convertor.EntityToDTOConvertor;
import com.bridgelab.addressbook.dto.PersonDTO;
import com.bridgelab.addressbook.model.Person;
import com.bridgelab.addressbook.service.PersonService;

@RestController
@RequestMapping("/addressbook")
public class Controller {

	@Autowired
	PersonService personService;

	@Autowired
	EntityToDTOConvertor convertor;


	@GetMapping("/get")
	public ResponseEntity<List<PersonDTO>> getAllPerson() {
		List<Person> allPerson = personService.getAllPerson();
		List<PersonDTO> dtoList = convertor.entityToDTO(allPerson);
		return new ResponseEntity<List<PersonDTO>>(dtoList, HttpStatus.OK);
	}

	@GetMapping("/get/{eId}")
	public ResponseEntity<PersonDTO> getPersonById(@PathVariable("eId") int eId) {
		Person person = personService.getPersonById(eId);
		PersonDTO personDTO = convertor.entityToDTO(person);
		return new ResponseEntity<PersonDTO>(personDTO, HttpStatus.OK);
	}

	@GetMapping("/get/name/{name}")
	public ResponseEntity<List<PersonDTO>> getPersonByName(@PathVariable("name") String name) {
		List<Person> personList = personService.getPersonByName(name);
		List<PersonDTO> personDtoList = convertor.entityToDTO(personList);
		return new ResponseEntity<List<PersonDTO>>(personDtoList, HttpStatus.OK);
	}

	@GetMapping("/get/city/{city}")
	public ResponseEntity<List<PersonDTO>> getPersonByCity(@PathVariable("city") String city) {
		List<Person> personList = personService.getPersonByCity(city);
		List<PersonDTO> personDtoList = convertor.entityToDTO(personList);
		return new ResponseEntity<List<PersonDTO>>(personDtoList, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<PersonDTO> addPerson(@Valid @RequestBody PersonDTO personDTO) {
		Person person = convertor.dtoToEntity(personDTO);
		Person savedPerson = personService.savePerson(person);
		PersonDTO persondto = convertor.entityToDTO(savedPerson);
		return new ResponseEntity<PersonDTO>(persondto, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") int id, @Valid @RequestBody PersonDTO personDto) {
		Person person = convertor.dtoToEntity(personDto);
		Person updatedPerson = personService.updatePerson(id, person);
		PersonDTO persondto = convertor.entityToDTO(updatedPerson);
		return new ResponseEntity<PersonDTO>(persondto, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable("id") int id) {
		personService.deletePerson(id);
		return new ResponseEntity<String>("Data deleted for id : " + id, HttpStatus.OK);
	}
}
