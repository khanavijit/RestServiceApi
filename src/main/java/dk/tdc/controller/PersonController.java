package dk.tdc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dk.tdc.entity.Person;
import dk.tdc.requests.PersonRequest;
import dk.tdc.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	
	@RequestMapping(value="/service/cpr/load", method = RequestMethod.POST)
	public ResponseEntity<String> loadAllProducts(){
		personService.loadAllPerson();
		return new ResponseEntity<String>("Data Loaded", new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@RequestMapping("/service/cpr/all")
	public List<Person> getAllDetails(){
		
		return personService.getAllDetails();
	}
	
	@RequestMapping("/service/cpr/{cprNr}")
	public Person getPersonByCprNr(@PathVariable String cprNr){
		
		return personService.getPersonByCpr(cprNr);
	}
		

	
	@RequestMapping(method=RequestMethod.POST,value="/service/cpr/validate")
	public ResponseEntity<String> validatePerson(@RequestBody PersonRequest request){
		Person person=personService.getPersonByCpr(request.getCprNr());
		
		System.out.println("Request : " + request );
		
		System.out.println("Response : " + person );
		
		if (person!=null &&  person.getFirstName() !=null && person.getLastName() !=null && person.getFirstName().equalsIgnoreCase(request.getFirstName()) && person.getLastName().equalsIgnoreCase(request.getLastName())) {
			return new ResponseEntity<String>("Validation Success", new HttpHeaders(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Validation Failure",new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	

	@RequestMapping(method=RequestMethod.POST,value="/service/cpr/validate2")
	public ResponseEntity<String> validatePerson2(@RequestBody PersonRequest request){
		Person person=personService.getPersonByCpr(request.getCprNr());
		
		System.out.println("Request : " + request );
		
		System.out.println("Response : " + person );
		
		if (person!=null) {
			return new ResponseEntity<String>("Validation Success", new HttpHeaders(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Validation Failure",new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
	
	
	@RequestMapping(method=RequestMethod.POST,value="/service/cpr/person")
	public ResponseEntity<String> addCourse(@RequestBody Person person){
		
		
		personService.addPerson(person);
		
		return new ResponseEntity<String>("Data Added", new HttpHeaders(),HttpStatus.OK);
	}
	
	
	
}
