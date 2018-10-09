package dk.tdc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dk.tdc.entity.Product;
import dk.tdc.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(value="/service/product/load", method = RequestMethod.POST)
	public ResponseEntity<String> loadAllProducts(){
		productService.loadAllProduct();
		return new ResponseEntity<String>("Data Loaded", new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@RequestMapping("/service/product")
	public List<Product> getAllDetails(){
		
		return productService.getAllDetails();
	}
	
	@RequestMapping("/service/product/{packageType}")
	public List<Product> getPersonByCprNr(@PathVariable String packageType){
		
		return productService.getProductsByPackageType(packageType);
	}
		

	
	
	
	
	
	@RequestMapping("/service/product/price/{price}")
	public List<Product> getPersonByCprNr(@PathVariable double price){
		
		return productService.getProductsByPrice(price);
	}
	
	
	
	
	
	@RequestMapping(method=RequestMethod.POST,value="/service/product")
	public ResponseEntity<String> addProduct(@RequestBody Product product){
		
		
		productService.addProduct(product);
		
		return new ResponseEntity<String>("Data Added", new HttpHeaders(),HttpStatus.OK);
	}
	
	
	
}
