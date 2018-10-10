package dk.tdc.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.tdc.entity.Product;
import dk.tdc.repository.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	
	public List<Product> getAllDetails(){
				
		
 		List<Product> products= new ArrayList<Product>();
 		 		
 		productRepository.findAll().forEach(products::add);
		
		return products;
	}
	
	
	public List<Product> getProductsByPrice(double price){
				
		
 		List<Product> products= new ArrayList<Product>();
 		productRepository.findAll().forEach(products::add);
 		
 		List<Product> results = products.stream()                
                .filter(x -> (Math.abs(price - x.getPrice()) < 20) )     
                .collect(Collectors.toList());            	 		
 		
		
		return results;
	}
	
	
	
public List<Product> getProductsByPackageType(String packageType){
				
		
 		List<Product> products= new ArrayList<Product>();
 		productRepository.findAll().forEach(products::add);
 		
 		List<Product> results = products.stream()                
                .filter(x -> packageType.equals(x.getPackageType()) )     
                .collect(Collectors.toList());            	 		
 		
		
		return results;
	}
	
	
	
	
	public Product getProductByPackageName(String packageName){
		
		return productRepository.findByPackageName(packageName);
	}
	
	
	public Product getProductByPackageId(String packageId){
		
		return productRepository.findByPackageId(packageId);
	}
	
	
	public void addProduct(Product product){
		productRepository.save(product);
	}
	
	
	
	public void loadAllProduct() {
		 JSONParser parser = new JSONParser();

	        try {

	            Object obj = parser.parse(new FileReader("product.json"));

	            JSONArray jsonarray = (JSONArray) obj;
	            Product prd;
	            List<Product> prdList= new ArrayList<Product>();
	            for (int i = 0; i < jsonarray.size(); i++) {
	            	prd= new Product();
		            JSONObject jsonObject= (JSONObject) jsonarray.get(i);
		            
		                    
		            
		            prd.setPackageId(String.valueOf(jsonObject.get("packageId")));
		            prd.setPackageName(String.valueOf(jsonObject.get("packageName")));
		            prd.setPackageType(String.valueOf(jsonObject.get("packageType")));
		            prd.setPrice(Double.parseDouble(String.valueOf(jsonObject.get("price"))));
		            
		          
		            
		           
		            
		            prd.setFeatureId(String.valueOf(jsonObject.get("featureId")));
		            prd.setDataPlan(String.valueOf(jsonObject.get("dataPlan")));
		            prd.setVoicePlan(String.valueOf(jsonObject.get("voicePlan")));
		          
		            prd.setInternationalRoming(Boolean.parseBoolean(String.valueOf(jsonObject.get("internationalRoming"))));
		            
		            System.out.println(prd);
		            
		           
		           
		            
		            prdList.add(prd);
		            
	            }
	            productRepository.saveAll((Iterable<Product>)prdList);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	}

	

}
