package dk.tdc.genResponse;

public class Parameters {
	private String cprNroriginal;

	private String cprNr;

	public String getCprNroriginal ()
    {
        return cprNroriginal;
    }

	public void setCprNroriginal(
	String cprNroriginal)
	{
		this.cprNroriginal = cprNroriginal;
	}

	public String getCprNr() {
		return cprNr;
	}

	public void setCprNr(String cprNr) {
		this.cprNr = cprNr;
	}

	@Override
	public String toString() {
		return "ClassPojo [cprNr.original = " + cprNroriginal + ", cprNr = " + cprNr + "]";
	}
}
