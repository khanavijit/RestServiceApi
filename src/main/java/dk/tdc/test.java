package dk.tdc;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class test {

	public static void main(String[] args) {
		 ObjectMapper mapper = new ObjectMapper();
	        ObjectReader reader = mapper.reader();
	        String jsonString = "{\"users\" : [{\"id\" : \"1\", \"name\" : \"stanley\", \"age\" : \"28\" }]}";
	        try {
	            JsonNode node = reader.readTree(jsonString);
	            System.out.println(node.toString());
	            ObjectNode objectNode = (ObjectNode) node;
	            objectNode.put("gender", "male");
	            System.out.println(node.toString());
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		

	}

}
