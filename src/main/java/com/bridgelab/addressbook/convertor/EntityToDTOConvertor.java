package com.bridgelab.addressbook.convertor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bridgelab.addressbook.dto.PersonDTO;
import com.bridgelab.addressbook.model.Person;

@Component
public class EntityToDTOConvertor {

	public Person dtoToEntity(PersonDTO personDTO) {
		ModelMapper mapper = new ModelMapper();
		Person person = mapper.map(personDTO, Person.class);
		return person;
	}

	public PersonDTO entityToDTO(Person person) {
		ModelMapper mapper = new ModelMapper();
		PersonDTO personDTO = mapper.map(person, PersonDTO.class);
		return personDTO;
	}

	public List<Person> dtoToEntity(List<PersonDTO> personDTOList) {
		return personDTOList.stream().map(dto -> dtoToEntity(dto)).collect(Collectors.toList());
	}

	public List<PersonDTO> entityToDTO(List<Person> personList) {
		return personList.stream().map(person -> entityToDTO(person)).collect(Collectors.toList());
	}
}
