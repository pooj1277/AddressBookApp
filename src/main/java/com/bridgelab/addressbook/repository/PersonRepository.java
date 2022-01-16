package com.bridgelab.addressbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelab.addressbook.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	List<Person> findByName(String name);

	List<Person> findByCity(String city);
}
