package dk.tdc.controller;

import java.io.IOException;
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
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dk.tdc.entity.Order;
import dk.tdc.entity.OrderItem;
import dk.tdc.entity.Person;
import dk.tdc.entity.Product;
import dk.tdc.requests.CartItem;
import dk.tdc.requests.PersonRequest;
import dk.tdc.service.OrderService;
import dk.tdc.service.PersonService;
import dk.tdc.service.ProductService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	
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
	
	@RequestMapping(value="/service/cpr1/", method = RequestMethod.POST)
	public ResponseEntity<String> getPersonByCprNr1(HttpEntity<String> httpEntity){
		
		
		System.out.println("Method CER 1");
		
		 	String reqObject = httpEntity.getBody();
		    String cpr="";
		    String productType=null;
		    
		    	    
		    
		    String packageId=null;
		    
			    
		    
		    JSONParser parser = new JSONParser();
		    JSONObject mainReq;
		    JsonNode rootNode=null;
		    
		    
		    String resp="{\"speech\": \"spoken response\",\"displayText\": \"displayed response\",\"messages\": [{\"speech\": \"Text response\",\"type\": 0}],\"source\": \"example.com\",\"data\": {\"google\": {\"expectUserResponse\": true,\"richResponse\": {\"items\": [{\"simpleResponse\": {\"textToSpeech\": \"this is a simple response\"}}]}},\"facebook\": {\"text\": \"Hello, Facebook!\"},\"slack\": {\"text\": \"This is a text response for Slack.\"}},\"contextOut\": [{\"name\": \"context name\",\"lifespan\": 5,\"parameters\": {\"param\": \"param value\"}}],\"followupEvent\": {\"name\": \"event name\",\"parameters\": {\"param\": \"param value\"}}}";

		    
		    
		    String type="";
		    
		    
			try {
				/*mapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				 CprRequest obj = mapper2.readValue(reqObject, CprRequest.class);*/
				    
//				    System.out.println("JSON OBJECT " + obj);
				
				
				mainReq = (JSONObject) parser.parse(reqObject);
//				System.out.println(mainReq);
				
				String queryResult=String.valueOf(mainReq.get("result"));
				
				 JSONObject qResult = (JSONObject) parser.parse(queryResult);
				
				String param=String.valueOf(qResult.get("parameters"));
				
				JSONObject paramJson = (JSONObject) parser.parse(param);
				
				cpr=String.valueOf(paramJson.get("cprNr"));
				
				productType=String.valueOf(paramJson.get("productType"));				
				
				System.out.println("Type1 "+type);
				
				packageId=String.valueOf(paramJson.get("packageId"));
				
				
				
				System.out.println("cpr" + cpr);
				System.out.println("productType" + productType);
				System.out.println("packageId" + packageId);
				
				if(cpr!=null && !cpr.equalsIgnoreCase("")) {
					type="CPR";
				}
				System.out.println("Type 2"+type);
				
				if(productType != null && ( productType.equalsIgnoreCase("voice") || productType.equalsIgnoreCase("data") ||  productType.equalsIgnoreCase("bundle")) ) {
					System.out.println("Inside Prodtype");
					type="PRODTYPE";
				}
//				System.out.println("Type 3: "+type + packageId!=null + ""+ !packageId.equalsIgnoreCase("") );
				System.out.println("Type 3"+type);
				if(packageId !=null && packageId.equalsIgnoreCase("2")) {
					type="ORD";
				}
				System.out.println("Type 4"+type);
				
				System.out.println(cpr + type + packageId + productType);
				
				if(type.equalsIgnoreCase("CPR")) {
					Person person = personService.getPersonByCpr(cpr);
					
					System.out.println("name " + person.getFirstName());
					

					
					ObjectMapper mapper = new ObjectMapper();
					rootNode = mapper.readTree(reqObject);    
					JsonPointer valueNodePointer = JsonPointer.compile("/result/speech");
//					JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text");
					
//					JSONArray jsonarray = (JSONArray) valueNodePointer.head();
					
					System.out.println("Avijit pointer 1" + valueNodePointer);
					
					JsonPointer containerPointer = valueNodePointer.head();
					
					System.out.println("Avijit pointer 2" + containerPointer);
					JsonNode parentJsonNode = rootNode.at(containerPointer);
					
					System.out.println("Avijit pointer 3" + parentJsonNode);
				
					
					if (!parentJsonNode.isMissingNode() && parentJsonNode.isObject()) {
					    ObjectNode parentObjectNode = (ObjectNode) parentJsonNode;
					    //following will give you just the field name. 
					    //e.g. if pointer is /grandObject/Object/field
					    //JsonPoint.last() will give you /field 
					    //remember to take out the / character 
					    String fieldName = valueNodePointer.last().toString();
					    fieldName = fieldName.replace(Character.toString(JsonPointer.SEPARATOR), "");
					    JsonNode fieldValueNode = parentObjectNode.get(fieldName);

					    if(fieldValueNode != null) {
					        parentObjectNode.put(fieldName, "HIIIIIIIIIIIIIIIIIIIIIIIIIIi");
					    }
					}
					
					
					
					JsonPointer valueNodePointer2 = JsonPointer.compile("/result/fulfillment/speech");
//					JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text");
					
//					JSONArray jsonarray = (JSONArray) valueNodePointer.head();
					
					System.out.println("Avijit pointer 1" + valueNodePointer2);
					
					JsonPointer containerPointer2 = valueNodePointer2.head();
					
					System.out.println("Avijit pointer 2" + containerPointer2);
					JsonNode parentJsonNode2 = rootNode.at(containerPointer2);
					
					System.out.println("Avijit pointer 3" + parentJsonNode2);
				
					
					if (!parentJsonNode2.isMissingNode() && parentJsonNode2.isObject()) {
					    ObjectNode parentObjectNode2 = (ObjectNode) parentJsonNode2;
					    //following will give you just the field name. 
					    //e.g. if pointer is /grandObject/Object/field
					    //JsonPoint.last() will give you /field 
					    //remember to take out the / character 
					    String fieldName = valueNodePointer2.last().toString();
					    fieldName = fieldName.replace(Character.toString(JsonPointer.SEPARATOR), "");
					    JsonNode fieldValueNode = parentObjectNode2.get(fieldName);

					    if(fieldValueNode != null) {
					        parentObjectNode2.put(fieldName, "HIIIIIIIIIIIIIIIIIITTTTTTTTTTTTTt");
					    }
					}
					
					
				
					    	

					resp=resp.replace("Text response", "Hi " +person.getFirstName() +" " + person.getLastName() + ", Please provide your contact nr. ? ");
					resp=resp.trim();
					    	
				}
				else if(type.equalsIgnoreCase("PRODTYPE")) {
					

					List<Product> products = productService.getProductsByPackageType(productType);
					String prodString="";
					for(Product product: products) {
						
						prodString+=product.getPackageId()+"."+product.getPackageName() +"( "+product.getPrice()+"kr.) ,";
					}
					 if (prodString != null && prodString.length() > 1) {
						 prodString = prodString.substring(0, prodString.length() - 1);
						 
						
						 String toReplace = ",";
						 String replacement = " and ";

						 int start = prodString.lastIndexOf(toReplace);
						 
						 
						 
						 
						 StringBuilder strb=new StringBuilder(prodString);    
						 int index=strb.lastIndexOf(toReplace);    
						 strb.replace(index,toReplace.length()+index,replacement);    
						 prodString= strb.toString();
						 
				     }
					 
					 

					
					ObjectMapper mapper = new ObjectMapper();
					rootNode = mapper.readTree(reqObject);    
					JsonPointer valueNodePointer = JsonPointer.compile("/result/speech");
//					JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text");
					
//					JSONArray jsonarray = (JSONArray) valueNodePointer.head();
					
					System.out.println("Avijit pointer 1" + valueNodePointer);
					
					JsonPointer containerPointer = valueNodePointer.head();
					
					System.out.println("Avijit pointer 2" + containerPointer);
					JsonNode parentJsonNode = rootNode.at(containerPointer);
					
					System.out.println("Avijit pointer 3" + parentJsonNode);
				
					
					if (!parentJsonNode.isMissingNode() && parentJsonNode.isObject()) {
					    ObjectNode parentObjectNode = (ObjectNode) parentJsonNode;
					    //following will give you just the field name. 
					    //e.g. if pointer is /grandObject/Object/field
					    //JsonPoint.last() will give you /field 
					    //remember to take out the / character 
					    String fieldName = valueNodePointer.last().toString();
					    fieldName = fieldName.replace(Character.toString(JsonPointer.SEPARATOR), "");
					    JsonNode fieldValueNode = parentObjectNode.get(fieldName);

					    if(fieldValueNode != null) {
					        parentObjectNode.put(fieldName, "HIIIIIIIIIIIIIIIIIIIIIIIIIIi");
					    }
					}
					
					
					
					JsonPointer valueNodePointer2 = JsonPointer.compile("/result/fulfillment/speech");
//					JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text");
					
//					JSONArray jsonarray = (JSONArray) valueNodePointer.head();
					
					System.out.println("Avijit pointer 1" + valueNodePointer2);
					
					JsonPointer containerPointer2 = valueNodePointer2.head();
					
					System.out.println("Avijit pointer 2" + containerPointer2);
					JsonNode parentJsonNode2 = rootNode.at(containerPointer2);
					
					System.out.println("Avijit pointer 3" + parentJsonNode2);
				
					
					if (!parentJsonNode2.isMissingNode() && parentJsonNode2.isObject()) {
					    ObjectNode parentObjectNode2 = (ObjectNode) parentJsonNode2;
					    //following will give you just the field name. 
					    //e.g. if pointer is /grandObject/Object/field
					    //JsonPoint.last() will give you /field 
					    //remember to take out the / character 
					    String fieldName = valueNodePointer2.last().toString();
					    fieldName = fieldName.replace(Character.toString(JsonPointer.SEPARATOR), "");
					    JsonNode fieldValueNode = parentObjectNode2.get(fieldName);

					    if(fieldValueNode != null) {
					        parentObjectNode2.put(fieldName, "HIIIIIIIIIIIIIIIIIITTTTTTTTTTTTTt");
					    }
					}
					
					
				
					    	
					
					resp=resp.replace("Text response", "Please choose among "+prodString);
					resp=resp.trim();
					    	
				
					
				}
				else if(type.equalsIgnoreCase("ORD")) {
					
					
					
					Order order = new Order();
					
					Person person=personService.getPersonByCpr(cpr);
					
					
					
					if(person==null) {
						return new ResponseEntity<String>("Order Failed - Invalid Person " , new HttpHeaders(),HttpStatus.NOT_FOUND);
					}
					
					
					
						
						OrderItem orderItem = new OrderItem();
						Product product=productService.getProductByPackageId(packageId);
						if(product==null) {
							return new ResponseEntity<String>("Order Failed - Invalid Product " , new HttpHeaders(),HttpStatus.NOT_FOUND);
						}
						
						orderItem.setProduct(product);
						order.addOrderItems(orderItem);
						
					
					order.setPerson(person);
					order.setOrderStatus("Created");
					
					String orderNr=orderService.addOrder(order);
					
		 

					
					ObjectMapper mapper = new ObjectMapper();
					rootNode = mapper.readTree(reqObject);    
					JsonPointer valueNodePointer = JsonPointer.compile("/result/speech");
//					JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text");
					
//					JSONArray jsonarray = (JSONArray) valueNodePointer.head();
					
					System.out.println("Avijit pointer 1" + valueNodePointer);
					
					JsonPointer containerPointer = valueNodePointer.head();
					
					System.out.println("Avijit pointer 2" + containerPointer);
					JsonNode parentJsonNode = rootNode.at(containerPointer);
					
					System.out.println("Avijit pointer 3" + parentJsonNode);
				
					
					if (!parentJsonNode.isMissingNode() && parentJsonNode.isObject()) {
					    ObjectNode parentObjectNode = (ObjectNode) parentJsonNode;
					    //following will give you just the field name. 
					    //e.g. if pointer is /grandObject/Object/field
					    //JsonPoint.last() will give you /field 
					    //remember to take out the / character 
					    String fieldName = valueNodePointer.last().toString();
					    fieldName = fieldName.replace(Character.toString(JsonPointer.SEPARATOR), "");
					    JsonNode fieldValueNode = parentObjectNode.get(fieldName);

					    if(fieldValueNode != null) {
					        parentObjectNode.put(fieldName, "HIIIIIIIIIIIIIIIIIIIIIIIIIIi");
					    }
					}
					
					
					
					JsonPointer valueNodePointer2 = JsonPointer.compile("/result/fulfillment/speech");
//					JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text");
					
//					JSONArray jsonarray = (JSONArray) valueNodePointer.head();
					
					System.out.println("Avijit pointer 1" + valueNodePointer2);
					
					JsonPointer containerPointer2 = valueNodePointer2.head();
					
					System.out.println("Avijit pointer 2" + containerPointer2);
					JsonNode parentJsonNode2 = rootNode.at(containerPointer2);
					
					System.out.println("Avijit pointer 3" + parentJsonNode2);
				
					
					if (!parentJsonNode2.isMissingNode() && parentJsonNode2.isObject()) {
					    ObjectNode parentObjectNode2 = (ObjectNode) parentJsonNode2;
					    //following will give you just the field name. 
					    //e.g. if pointer is /grandObject/Object/field
					    //JsonPoint.last() will give you /field 
					    //remember to take out the / character 
					    String fieldName = valueNodePointer2.last().toString();
					    fieldName = fieldName.replace(Character.toString(JsonPointer.SEPARATOR), "");
					    JsonNode fieldValueNode = parentObjectNode2.get(fieldName);

					    if(fieldValueNode != null) {
					        parentObjectNode2.put(fieldName, "HIIIIIIIIIIIIIIIIIITTTTTTTTTTTTTt");
					    }
					}
					
					
				
					    	
					
					resp=resp.replace("Text response", "You order has been successfully created.Your order Nr is : "+orderNr);
					resp=resp.trim();
					    	
				
					
				}
				
				
				 
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			String tst="{\"allRequiredParamsPresent\":true,\"fulfillmentMessages\":";
		/*
			String resp="{\"speech\": \"spoken response\",\"displayText\": \"displayed response\",\"messages\": [{\"speech\": \"Text response\",\"type\": 0}],\"source\": \"example.com\",\"data\": {\"google\": {\"expectUserResponse\": true,\"richResponse\": {\"items\": [{\"simpleResponse\": {\"textToSpeech\": \"this is a simple response\"}}]}},\"facebook\": {\"text\": \"Hello, Facebook!\"},\"slack\": {\"text\": \"This is a text response for Slack.\"}},\"contextOut\": [{\"name\": \"context name\",\"lifespan\": 5,\"parameters\": {\"param\": \"param value\"}}],\"followupEvent\": {\"name\": \"event name\",\"parameters\": {\"param\": \"param value\"}}}";

			resp.replace("Text response", "Hi " +person.getFirstName() +" " + person.getLastName() + ", Please Choose Product!");
			resp=resp.trim();*/
			 ObjectMapper mapper2a = new ObjectMapper();
		    ObjectReader reader = mapper2a.reader();
		        
			
			  JsonNode node=null;
			try {
				node = reader.readTree(resp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            System.out.println(node.toString());

//			return new ResponseEntity<String>(rootNode.toString(), new HttpHeaders(),HttpStatus.OK);
			
			return new ResponseEntity<String>(resp, new HttpHeaders(),HttpStatus.OK);
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
		    
		    
		    String respId="";
		    String outputcnxt="";
		    String inttent="";
		    String session="";
		    
		    
//		    String resp="{\"fulfillmentText\": \"displayed&spoken response\",\"fulfillmentMessages\": [{\"text\": [\"text response\"]}],\"source\": \"example.com\",\"payload\": {\"google\": {\"expectUserResponse\": true,\"richResponse\": {\"items\": [{\"simpleResponse\": {\"textToSpeech\": \"this is a simple response\"}}]}},\"facebook\": {\"text\": \"Hello, Facebook!\"},\"slack\": {\"text\": \"This is a text response for Slack.\"}},\"outputContexts\": [{\"name\": \"projects/${PROJECT_ID}/agent/sessions/${SESSION_ID}/contexts/context name\",\"lifespanCount\": 5,\"parameters\": {\"param\": \"param value\"}}],\"followupEventInput\": {\"name\": \"event name\",\"languageCode\": \"en-US\",\"parameters\": {\"param\": \"param value\"}}}";
		    
//		    String resp="{  \"fulfillmentText\": \"displayed&spoken response\",  \"fulfillmentMessages\": [    {      \"text\": {        \"text\": [          \"Text defined in Dialogflow's console for the intent that was matched\"        ]      }    }  ],  \"source\": \"example.com\",  \"payload\": {    \"google\": {      \"expectUserResponse\": true,      \"richResponse\": {        \"items\": [          {            \"simpleResponse\": {              \"textToSpeech\": \"this is a simple response\"            }          }        ]      }    },    \"facebook\": {      \"text\": \"Hello, Facebook!\"    },    \"slack\": {      \"text\": \"This is a text response for Slack.\"    }  },  \"outputContexts\": [    {      \"name\": \"projects/${PROJECT_ID}/agent/sessions/${SESSION_ID}/contexts/context name\",      \"lifespanCount\": 5,      \"parameters\": {        \"param\": \"param value\"      }    }  ],  \"followupEventInput\": {    \"name\": \"event name\",    \"languageCode\": \"en-US\",    \"parameters\": {      \"param\": \"param value\"    }  }}";
		    
//		    String resp="{  \"fulfillmentText\": \"displayedspoken response\",  \"fulfillmentMessages\": [    {   \"platform\": \"ACTIONS_ON_GOOGLE\",   \"text\": {        \"text\":     [      \"Text defined in Dialogflow's console for the intent that was matched\"     ]        }    }  ],  \"source\": \"example.com\",  \"payload\": {    \"google\": {      \"expectUserResponse\": true,      \"richResponse\": {        \"items\": [          {            \"simpleResponse\": {              \"textToSpeech\": \"this is a simple response\"            }          }        ]      }    },    \"facebook\": {      \"text\": \"Hello, Facebook!\"    },    \"slack\": {      \"text\": \"This is a text response for Slack.\"    }  },  \"outputContexts\": [    {      \"name\": \"projects/${PROJECT_ID}/agent/sessions/${SESSION_ID}/contexts/context name\",      \"lifespanCount\": 5,      \"parameters\": {        \"param\": \"param value\"      }    }  ],  \"followupEventInput\": {    \"name\": \"event name\",    \"languageCode\": \"en-US\",    \"parameters\": {      \"param\": \"param value\"    }  }}";
		    
			   
		    String resp="{  \"responseId\": \"~~RESPID~~\",  \"queryResult\": {    \"queryText\": \"0709863896\",    \"action\": \"cprCapture\",    \"parameters\": {      \"cprNr\": \"0709863896\"    },    \"allRequiredParamsPresent\": true,    \"fulfillmentText\": \"~~MSG~~\",    \"fulfillmentMessages\": [      {        \"text\": {          \"text\": [            \"~~MSG~~\"          ]        }      }    ],    \"outputContexts\": ~~OUTPUTCNXT~~,    \"intent\": ~~INTNAME~~,    \"intentDetectionConfidence\": 1,    \"languageCode\": \"en\"  },  \"originalDetectIntentRequest\": {    \"payload\": {}  },  \"session\": \"~~SESSION~~\"}";
		    
		    
			try {
				/*mapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				 CprRequest obj = mapper2.readValue(reqObject, CprRequest.class);*/
				    
//				    System.out.println("JSON OBJECT " + obj);
				
				
				mainReq = (JSONObject) parser.parse(reqObject);
//				System.out.println(mainReq);
				
				respId=String.valueOf(mainReq.get("responseId"));
				
				session=String.valueOf(mainReq.get("session"));
				
				String queryResult=String.valueOf(mainReq.get("queryResult"));
				
				 JSONObject qResult = (JSONObject) parser.parse(queryResult);
				
				String param=String.valueOf(qResult.get("parameters"));
				
				JSONObject paramJson = (JSONObject) parser.parse(param);
				
				
				outputcnxt=String.valueOf(qResult.get("outputContexts"));
				
				inttent=String.valueOf(qResult.get("intent"));
				
				
				
				
				cpr=String.valueOf(paramJson.get("cprNr"));
				
				System.out.println(cpr);
				
				System.out.println(outputcnxt);
				System.out.println(inttent);
				
				System.out.println(session);
				
				Person person = personService.getPersonByCpr(cpr);
				
				System.out.println("name " + person.getFirstName());
				

				
			
				
				
				ObjectMapper mapper = new ObjectMapper();
				rootNode = mapper.readTree(reqObject);    
				JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text/text");
//				JsonPointer valueNodePointer = JsonPointer.compile("/queryResult/fulfillmentMessages/0/text");
				
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
				    		arrnode.add("Hi " +person.getFirstName() +" " + person.getLastName() + ", Please Choose Product!");
				    		
				    	
						
						
						
						
						
						}
				    	
				    	else {/*
				    		
				    		 ObjectMapper mapper2a = new ObjectMapper();
						        ObjectReader reader = mapper2a.reader();
						        

					    		String resp="{\"speech\":\"~~HH~~\",\"displayText\":\"~~jj~~\",\"source\": \"game schedule\"}";

						    	String hs="Hi " +person.getFirstName() +" " + person.getLastName() + ", Please Choose Product!";

							resp=resp.replace("~~HH~~", hs);
							resp=resp.replace("~~jj~~", hs);
//							
							resp=resp.replace("", "");
							
							
//							 String jsonString = "{\"users\" : [{\"id\" : \"1\", \"name\" : \"stanley\", \"age\" : \"28\" }]}";
						        try {
						            JsonNode node = reader.readTree(resp);
						            System.out.println(node.toString());
						            ObjectNode objectNode = (ObjectNode) node;
						            objectNode.put("gender", "male");
						            System.out.println(node.toString());
						        } catch (JsonProcessingException e) {
						            e.printStackTrace();
						        } catch (IOException e) {
						            e.printStackTrace();
						        }

				    	*/}
				    	
				    	
				    	
				    	
				    	
				    	
				    	

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
				resp=resp.replace("~~MSG~~", "Hi " +person.getFirstName() +" " + person.getLastName() + ", Please Choose Product!");
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		
//			String resp="{\"fulfillmentText\": \"This is a text response\",\"fulfillmentMessages\": [{\"card\": {\"title\": \"card title\",\"subtitle\": \"card text\",\"imageUri\": \"Molecule-Formation-stop.png\",\"buttons\": [{\"text\": \"button text\"}]}}],\"source\": \"example.com\",\"payload\": {\"google\": {\"expectUserResponse\": true,\"richResponse\": {\"items\": [{\"simpleResponse\": {\"textToSpeech\": \"this is a simple response\"}}]}},\"facebook\": {\"text\": \"Hello, Facebook!\"},\"slack\": {\"text\": \"This is a text response for Slack.\"}},\"outputContexts\": [{\"name\": \"projects/${PROJECT_ID}/agent/sessions/${SESSION_ID}/contexts/context name\",\"lifespanCount\": 5,\"parameters\": {\"param\": \"param value\"}}],\"followupEventInput\": {\"name\": \"event name\",\"languageCode\": \"en-US\",\"parameters\": {\"param\": \"param value\"}}}";

			resp=resp.replace("~~RESPID~~", respId);
			
			resp=resp.replace("~~OUTPUTCNXT~~", outputcnxt);
			
			resp=resp.replace("~~INTNAME~~", inttent);
			
			resp=resp.replace("~~MSG~~", respId);
			
			resp=resp.replace("~~SESSION~~", session);
			resp=resp.trim();
			

			return new ResponseEntity<String>(resp, new HttpHeaders(),HttpStatus.OK);
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
