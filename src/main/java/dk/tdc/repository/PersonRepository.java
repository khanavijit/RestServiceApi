package dk.tdc.repository;

import org.springframework.data.repository.CrudRepository;

import dk.tdc.entity.Person;

public interface PersonRepository extends CrudRepository <Person,Integer>{
	
	
	public Person findByCprNr(String cprNr);

}
