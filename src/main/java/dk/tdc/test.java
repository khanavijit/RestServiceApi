package dk.tdc;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class test {

	public static void main(String[] args) {
		String resp="{\"speech\": \"spoken response\",\"displayText\": \"displayed response\",\"messages\": [{\"speech\": \"~~TXT~~~\",\"type\": 0}],\"source\": \"example.com\",\"data\": {\"google\": {\"expectUserResponse\": true,\"richResponse\": {\"items\": [{\"simpleResponse\": {\"textToSpeech\": \"this is a simple response\"}}]}},\"facebook\": {\"text\": \"Hello, Facebook!\"},\"slack\": {\"text\": \"This is a text response for Slack.\"}},\"contextOut\": [{\"name\": \"context name\",\"lifespan\": 5,\"parameters\": {\"param\": \"param value\"}}],\"followupEvent\": {\"name\": \"event name\",\"parameters\": {\"param\": \"param value\"}}}";

		resp=resp.replace("~~TXT~~~", "Hi ");
		resp=resp.trim();
		  
		System.out.println(resp);
		

	}

}
