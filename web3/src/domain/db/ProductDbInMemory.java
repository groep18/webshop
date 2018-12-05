package domain.db;

import domain.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDbInMemory implements ProductDB {
	private Map<String, Product> products = new HashMap<String, Product>();

	public ProductDbInMemory () {
		Product p1 = new Product("001", "iphone","2de hands", 99.90);
		Product p2 = new Product("002", "Samsung","nieuw", 10.00);
		Product p3 = new Product("003", "OnePlus","derdehand", 29.95);

		add(p1);
		add(p2);
		add(p3);
	}

	@Override
	public Product get(String productId){
		if(productId == null){
			throw new IllegalArgumentException("No id given.");
		}
		return products.get(productId);
	}

	@Override
	public List<Product> getAll(){
		return new ArrayList<Product>(products.values());
	}

	@Override
	public void add(Product product){
		if(product == null){
			throw new IllegalArgumentException("No product given.");
		}
		if (products.containsKey(product.getId())) {
			throw new IllegalArgumentException("Product already exists.");
		}
		products.put(product.getId(), product);
	}

	@Override
	public void update(Product product){
		if(product == null){
			throw new IllegalArgumentException("No product given.");
		}
		if(!products.containsKey(product.getId())){
			throw new IllegalArgumentException("No product found.");
		}
		products.put(product.getId(), product);
	}

	@Override
	public void delete(String productId){
		if(productId == null){
			throw new IllegalArgumentException("No id given.");
		}
		products.remove(productId);
	}
}
