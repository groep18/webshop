package domain.db;

import domain.model.Product;

import java.util.List;

public interface ProductDB {

    void add(Product product) throws DbException;
    Product get(String id) throws DbException;
    List<Product> getAll() throws DbException;
    void update(Product product) throws DbException;
    void delete(String productId) throws DbException;
}
