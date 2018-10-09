package dk.tdc.requests;

public class PersonRequest {

	@Override
	public String toString() {
		return "PersonRequest [cprNr=" + cprNr + ", firstName=" + firstName + ", lastName=" + lastName + ", streetName="
				+ streetName + ", zipCode=" + zipCode + "]";
	}

	private String cprNr;
	private String firstName;
	private String lastName; 
	private String streetName;
	private String zipCode;
	public String getCprNr() {
		return cprNr;
	}
	public void setCprNr(String cprNr) {
		this.cprNr = cprNr;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public PersonRequest(String cprNr, String firstName, String lastName, String streetName, String zipCode) {
		super();
		this.cprNr = cprNr;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetName = streetName;
		this.zipCode = zipCode;
	}
}
