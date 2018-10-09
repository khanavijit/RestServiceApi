package dk.tdc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

	@Override
	public String toString() {
		return "Person [cprNr=" + cprNr + ", city=" + city + ", country=" + country + ", department=" + department
				+ ", email=" + email + ", firstName=" + firstName + ", floor=" + floor + ", houseLetter=" + houseLetter
				+ ", houseNo=" + houseNo + ", lastName=" + lastName + ", mailDistrict=" + mailDistrict
				+ ", municipalityCode=" + municipalityCode + ", side=" + side + ", streetCode=" + streetCode
				+ ", streetName=" + streetName + ", zipCode=" + zipCode + "]";
	}


	@Id	
	private String cprNr;
	private String city;
	private String country;
	private String department;
	private String email;
	private String firstName;
	private String floor;	
	private String houseLetter;
	private String houseNo;
	private String lastName;               
	private String mailDistrict;
	private String municipalityCode;
	private String side;
	private String streetCode;
	private String streetName;
	private String zipCode;
	
	
	
	
	public Person() {
		
	}
	
	
	public Person(String cprNr, String city, String country, String department, String email, String firstName,
			String floor, String houseLetter, String houseNo, String lastName, String mailDistrict,
			String municipalityCode, String side, String streetCode, String streetName, String zipCode) {
		super();
		
		this.cprNr = cprNr;
		this.city = city;
		this.country = country;
		this.department = department;
		this.email = email;
		this.firstName = firstName;
		this.floor = floor;
		this.houseLetter = houseLetter;
		this.houseNo = houseNo;
		this.lastName = lastName;
		this.mailDistrict = mailDistrict;
		this.municipalityCode = municipalityCode;
		this.side = side;
		this.streetCode = streetCode;
		this.streetName = streetName;
		this.zipCode = zipCode;
	}


	


	public String getCprNr() {
		return cprNr;
	}


	public void setCprNr(String cprNr) {
		this.cprNr = cprNr;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getFloor() {
		return floor;
	}


	public void setFloor(String floor) {
		this.floor = floor;
	}


	public String getHouseLetter() {
		return houseLetter;
	}


	public void setHouseLetter(String houseLetter) {
		this.houseLetter = houseLetter;
	}


	public String getHouseNo() {
		return houseNo;
	}


	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMailDistrict() {
		return mailDistrict;
	}


	public void setMailDistrict(String mailDistrict) {
		this.mailDistrict = mailDistrict;
	}


	public String getMunicipalityCode() {
		return municipalityCode;
	}


	public void setMunicipalityCode(String municipalityCode) {
		this.municipalityCode = municipalityCode;
	}


	public String getSide() {
		return side;
	}


	public void setSide(String side) {
		this.side = side;
	}


	public String getStreetCode() {
		return streetCode;
	}


	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
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
	
	
	
	
	
	
	
}
