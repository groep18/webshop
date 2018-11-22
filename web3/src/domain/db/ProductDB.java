package domain.db;

import domain.model.Product;

import java.util.List;

public interface ProductDB {

    void add(Product product);

    List<Product> getAll();

}
