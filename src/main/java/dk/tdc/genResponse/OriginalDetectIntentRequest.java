package dk.tdc.genResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OriginalDetectIntentRequest {
	
	@JsonProperty("payload")
	private String payload;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "ClassPojo [payload = " + payload + "]";
	}
}
