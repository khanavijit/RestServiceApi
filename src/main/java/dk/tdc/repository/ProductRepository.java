package dk.tdc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dk.tdc.entity.Product;

public interface ProductRepository extends CrudRepository <Product,Integer>{
	
	
	public Product findByPackageName(String packageName);
	public Product findByPackageId(String packageId);
	
	
	public List<Product> findByPackageType(String packageType);
	
	

	 

}
