package domain.db;

import domain.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDbInMemory implements ProductDB {
	private Map<Integer, Product> records = new HashMap<>();
	
	public ProductDbInMemory () {
		Product nothing = new Product("Nothing", "Nothing", 20);
		Product n1 = new Product("n1", "nzaef", 10);
		Product n2 = new Product("n2", "qsdf", 5);
		add(nothing);
		add(n1);
		add(n2);
	}
	
	public Product get(int id){
		if(id < 0){
			throw new DbException("No valid id given");
		}
		return records.get(id);
	}
	
	public List<Product> getAll(){
		return new ArrayList<Product>(records.values());	
	}

	public void add(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		int id = records.size() + 1;
		product.setProductId(id);
		if (records.containsKey(product.getProductId())) {
			throw new DbException("Product already exists");
		}
		records.put(product.getProductId(), product);
	}
	
	public void update(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		if(!records.containsKey(product.getProductId())){
			throw new DbException("No product found");
		}
		records.put(product.getProductId(), product);
	}
	
	public void delete(int id){
		if(id < 0){
			throw new DbException("No valid id given");
		}
		records.remove(id);
	}

	public int getNumberOfProducts() {
		return records.size();
	}
}
