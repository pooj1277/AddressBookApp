package com.bridgelab.addressbook.service;

import java.util.List;

import com.bridgelab.addressbook.model.Person;

public interface IPersonService {

	List<Person> getAllPerson();
	
	Person getPersonById(int id);
	
	Person savePerson(Person person);
	
	Person updatePerson(int id, Person person);
	
	void deletePerson(int id);
	
	List<Person> getPersonByName(String name);
	
	List<Person> getPersonByCity(String city);
}
