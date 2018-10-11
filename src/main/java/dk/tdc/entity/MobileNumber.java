package dk.tdc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import dk.tdc.customSequence.MySequence;

@Entity
public class MobileNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mobile_seq")
	@GenericGenerator(
		        name = "mobile_seq", 
		        strategy = "dk.tdc.customSequence.MySequenceMobile", 
		        parameters = {
		            @Parameter(name = MySequence.INCREMENT_PARAM, value = "1"),
		            @Parameter(name = MySequence.VALUE_PREFIX_PARAMETER, value = "CB_"),
		            @Parameter(name = MySequence.NUMBER_FORMAT_PARAMETER, value = "%05d") })
  private Long number;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
}