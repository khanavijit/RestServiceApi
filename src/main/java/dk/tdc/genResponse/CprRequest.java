package dk.tdc.genResponse;

public class CprRequest
{
    private String responseId;

    private String session;

    private QueryResult queryResult;

    private OriginalDetectIntentRequest originalDetectIntentRequest;

    public String getResponseId ()
    {
        return responseId;
    }

    public void setResponseId (String responseId)
    {
        this.responseId = responseId;
    }

    public String getSession ()
    {
        return session;
    }

    public void setSession (String session)
    {
        this.session = session;
    }

    public QueryResult getQueryResult ()
    {
        return queryResult;
    }

    public void setQueryResult (QueryResult queryResult)
    {
        this.queryResult = queryResult;
    }

    public OriginalDetectIntentRequest getOriginalDetectIntentRequest ()
    {
        return originalDetectIntentRequest;
    }

    public void setOriginalDetectIntentRequest (OriginalDetectIntentRequest originalDetectIntentRequest)
    {
        this.originalDetectIntentRequest = originalDetectIntentRequest;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responseId = "+responseId+", session = "+session+", queryResult = "+queryResult+", originalDetectIntentRequest = "+originalDetectIntentRequest+"]";
    }
}
	
