package dk.tdc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
	
	@RequestMapping(value="/service/cpr2/", method = RequestMethod.POST)
	public ResponseEntity<String> getPersonByCprNr2(HttpEntity<String> httpEntity){
		
		 	String reqObject = httpEntity.getBody();
//		    System.out.println("request json object = "+reqObject);
		    String cpr="";
		    
		    
		    
		    
		    
//		    ObjectMapper mapper2 = new ObjectMapper();

		    
		   
		    
		    
		    
		    
		    JSONParser parser = new JSONParser();
		    JSONObject mainReq;
		    JsonNode rootNode=null;
		    
		    
		    
		    
		    
		    
		    
		    
			try {
				/*mapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				 CprRequest obj = mapper2.readValue(reqObject, CprRequest.class);*/
				    
//				    System.out.println("JSON OBJECT " + obj);
				
				
				mainReq = (JSONObject) parser.parse(reqObject);
//				System.out.println(mainReq);
				
				String queryResult=String.valueOf(mainReq.get("queryResult"));
				
				 JSONObject qResult = (JSONObject) parser.parse(queryResult);
				
				String param=String.valueOf(qResult.get("parameters"));
				
				JSONObject paramJson = (JSONObject) parser.parse(param);
				
				cpr=String.valueOf(paramJson.get("cprNr"));
				
				System.out.println(cpr);
				
				Person person = personService.getPersonByCpr(cpr);
				
				System.out.println("name " + person.getFirstName());
				

				
			
				
				
				ObjectMapper mapper = new ObjectMapper();
				rootNode = mapper.readTree(reqObject);    
				JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text/text");
				
//				JSONArray jsonarray = (JSONArray) valueNodePointer.head();
				
				System.out.println("Avijit pointer 1" + valueNodePointer);
				
				JsonPointer containerPointer = valueNodePointer.head();
				
				System.out.println("Avijit pointer 2" + containerPointer);
				JsonNode parentJsonNode = rootNode.at(containerPointer);
				
				System.out.println("Avijit pointer 3" + parentJsonNode);

				if (!parentJsonNode.isMissingNode() && parentJsonNode.isObject()) {
					
					
					
				    ObjectNode parentObjectNode = (ObjectNode) parentJsonNode;
				   
				    String fieldName = valueNodePointer.last().toString();
				    fieldName = fieldName.replace(Character.toString(JsonPointer.SEPARATOR), "");
				    JsonNode fieldValueNode = parentObjectNode.get(fieldName);
				    
				    
				    
				   

				    if(fieldValueNode != null) {
				    	
				    	System.out.println("Avijit pointer 3" + fieldValueNode);
				    	
				    	if (fieldValueNode.isArray()) {
				    		ArrayNode arrnode=(ArrayNode)fieldValueNode;
				    		
				    		arrnode.removeAll();
				    		
				    		String resp="{\"speech\":\"~~HH~~\",\"displayText\":\"~~jj~~\",\"source\": \"game schedule\"}";

					    	String hs="Hi " +person.getFirstName() +" " + person.getLastName() + ", Please Choose Product!";

						resp=resp.replace("~~HH~~", hs);
						resp=resp.replace("~~jj~~", hs);
						
						resp=resp.replace("\\", "");
						
						
//						JSONObject jsonObj = (JSONObject) parser.parse(resp);
						
						System.out.println();
				    		
				    		arrnode.add(resp);
				    		
						    for (JsonNode objNode : fieldValueNode) {
						        System.out.println(objNode);
						    }
						}

				    	String queryResult1=String.valueOf(rootNode.get("queryResult"));
						
						 JSONObject qResult1 = (JSONObject) parser.parse(queryResult1);
				    	
				    	ObjectMapper mapper0 = new ObjectMapper(); 
						JsonNode node = mapper0.convertValue(qResult1, JsonNode.class);
						
						System.out.println(qResult);
						
						ObjectNode o = (ObjectNode) node;
						o.put("fulfillmentText", "Hi " +person.getFirstName() +" " + person.getLastName() + ", Please Choose Product!");
						
						
						System.out.println("dfg "+rootNode);
						
				    	
				    	
				    	
				    					    }
				}
				
				
				System.out.println(rootNode);
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			String tst="{\"allRequiredParamsPresent\":true,\"fulfillmentMessages\":";
		
			String resp="{\"speech\":\"speech Cant\",\"displayText\":\"Cant handle\",\"source\": \"game schedule\"}";



			return new ResponseEntity<String>(rootNode.toString(), new HttpHeaders(),HttpStatus.OK);
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
