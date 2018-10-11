package dk.tdc.genResponse;

public class QueryResult {
	private String languageCode;

	private String intentDetectionConfidence;

	private OutputContexts[] outputContexts;

	private String allRequiredParamsPresent;

	private String fulfillmentText;

	private Parameters parameters;

	private FulfillmentMessages[] fulfillmentMessages;

	private String queryText;

	private Intent intent;

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getIntentDetectionConfidence() {
		return intentDetectionConfidence;
	}

	public void setIntentDetectionConfidence(String intentDetectionConfidence) {
		this.intentDetectionConfidence = intentDetectionConfidence;
	}

	public OutputContexts[] getOutputContexts() {
		return outputContexts;
	}

	public void setOutputContexts(OutputContexts[] outputContexts) {
		this.outputContexts = outputContexts;
	}

	public String getAllRequiredParamsPresent() {
		return allRequiredParamsPresent;
	}

	public void setAllRequiredParamsPresent(String allRequiredParamsPresent) {
		this.allRequiredParamsPresent = allRequiredParamsPresent;
	}

	public String getFulfillmentText() {
		return fulfillmentText;
	}

	public void setFulfillmentText(String fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public FulfillmentMessages[] getFulfillmentMessages() {
		return fulfillmentMessages;
	}

	public void setFulfillmentMessages(FulfillmentMessages[] fulfillmentMessages) {
		this.fulfillmentMessages = fulfillmentMessages;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	@Override
	public String toString() {
		return "ClassPojo [languageCode = " + languageCode + ", intentDetectionConfidence = "
				+ intentDetectionConfidence + ", outputContexts = " + outputContexts + ", allRequiredParamsPresent = "
				+ allRequiredParamsPresent + ", fulfillmentText = " + fulfillmentText + ", parameters = " + parameters
				+ ", fulfillmentMessages = " + fulfillmentMessages + ", queryText = " + queryText + ", intent = "
				+ intent + "]";
	}
}
