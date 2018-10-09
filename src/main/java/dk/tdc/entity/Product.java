package dk.tdc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	

	public Product() {
		super();
	}


	@Id	
	private String packageId;
	private String packageName;
	private String packageType;
	private double price;
	
	
	private String featureId;
	private String dataPlan;
	private String voicePlan;
	private boolean internationalRoming;



	public String getFeatureId() {
		return featureId;
	}



	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}



	public String getDataPlan() {
		return dataPlan;
	}



	public void setDataPlan(String dataPlan) {
		this.dataPlan = dataPlan;
	}



	public String getVoicePlan() {
		return voicePlan;
	}



	public void setVoicePlan(String voicePlan) {
		this.voicePlan = voicePlan;
	}



	public boolean isInternationalRoming() {
		return internationalRoming;
	}



	public void setInternationalRoming(boolean internationalRoming) {
		this.internationalRoming = internationalRoming;
	}



	public String getPackageId() {
		return packageId;
	}



	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}



	public String getPackageName() {
		return packageName;
	}



	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}



	public String getPackageType() {
		return packageType;
	}



	



	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public Product(String packageId, String packageName, String packageType, double price, String featureId,
			String dataPlan, String voicePlan, boolean internationalRoming) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageType = packageType;
		this.price = price;
		this.featureId = featureId;
		this.dataPlan = dataPlan;
		this.voicePlan = voicePlan;
		this.internationalRoming = internationalRoming;
	}



	
	
	
	
	
	
}
