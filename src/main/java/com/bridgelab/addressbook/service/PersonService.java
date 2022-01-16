package com.bridgelab.addressbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelab.addressbook.exception.AddressBookException;
import com.bridgelab.addressbook.model.Person;
import com.bridgelab.addressbook.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {

	public List<Person> personList = new ArrayList<Person>();

	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Person> getAllPerson() {
		List<Person> personList = personRepository.findAll();
		if (personList.isEmpty()) {
			throw new AddressBookException("There isn't any data of the Employee, Plz add the Employee Data first.");
		}
		return personList;
	}

	@Override
	public Person getPersonById(int id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new AddressBookException("Data is not present for ID : " + id));
	}

	@Override
	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person updatePerson(int id, Person person) {
		Person per = this.getPersonById(id);
		per.setName(person.getName());
		per.setEmail(person.getEmail());
		per.setCity(person.getCity());
		per.setMobileNo(person.getMobileNo());
		return per;
	}

	@Override
	public void deletePerson(int id) {
		if (personRepository.existsById(id)) {
			personRepository.deleteById(id);
		} else
			throw new AddressBookException("Person data is not present for ID : " + id);
	}

	@Override
	public List<Person> getPersonByName(String name) {
		List<Person> personList = personRepository.findByName(name);
		if (personList.isEmpty()) {
			throw new AddressBookException("Data is not present for this name : " + name);
		} else
			return personList;
	}

	@Override
	public List<Person> getPersonByCity(String city) {
		List<Person> personList = personRepository.findByCity(city);
		if (personList.isEmpty()) {
			throw new AddressBookException("Data is not present for this city : " + city);
		} else
			return personList;
	}
}
