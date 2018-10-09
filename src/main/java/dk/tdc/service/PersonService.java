package dk.tdc.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.tdc.entity.Person;
import dk.tdc.repository.PersonRepository;


@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	
	
	
	public List<Person> getAllDetails(){
		
		
 		List<Person> persons= new ArrayList<Person>();
 		 		
 		personRepository.findAll().forEach(persons::add);
		
		return persons;
	}
	
	public Person getPersonByCpr(String cprNr){
		
		return personRepository.findByCprNr(cprNr);
	}
	
	public void addPerson(Person person){
		personRepository.save(person);
	}
	
	
	
	public void loadAllPerson() {
		 JSONParser parser = new JSONParser();

	        try {

	            Object obj = parser.parse(new FileReader("person.json"));

	            JSONArray jsonarray = (JSONArray) obj;
	            Person usr= new Person();
	            List<Person> usrList= new ArrayList<Person>();
	            for (int i = 0; i < jsonarray.size(); i++) {
	            	usr= new Person();
		            JSONObject jsonObject= (JSONObject) jsonarray.get(i);
		            
		                    
		            
		            usr.setCprNr(String.valueOf(jsonObject.get("cprNr")));
		            usr.setCity(String.valueOf(jsonObject.get("city")));
		            usr.setCountry(String.valueOf(jsonObject.get("country")));
		            usr.setDepartment(String.valueOf(jsonObject.get("department")));
		            usr.setEmail(String.valueOf(jsonObject.get("email")));
		            usr.setFirstName(String.valueOf(jsonObject.get("firstName")));
		            usr.setFloor(String.valueOf(jsonObject.get("floor")));
		            usr.setHouseLetter(String.valueOf(jsonObject.get("houseLetter")));
		            usr.setHouseNo(String.valueOf(jsonObject.get("houseNo")));
		            usr.setLastName(String.valueOf(jsonObject.get("lastName")));
		            usr.setMailDistrict(String.valueOf(jsonObject.get("mailDistrict")));
		            usr.setMunicipalityCode(String.valueOf(jsonObject.get("municipalityCode")));
		            usr.setSide(String.valueOf(jsonObject.get("side")));
		            usr.setStreetCode(String.valueOf(jsonObject.get("streetCode")));
		            usr.setStreetName(String.valueOf(jsonObject.get("streetName")));
		            usr.setZipCode(String.valueOf(jsonObject.get("zipCode")));
		            
		           
		            
		            usrList.add(usr);
		            
	            }
	            personRepository.saveAll((Iterable<Person>)usrList);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	}

	

}
