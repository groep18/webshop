package domain.service;

import domain.db.*;
import domain.model.DomainException;
import domain.model.Product;
import domain.model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDBSQL personDb;
    private ProductDBSQL productDb;

    public ShopService(Properties properties) {
        personDb = new PersonDBSQL(properties);
        productDb = new ProductDBSQL(properties);
    }
    // PERSON

    public User getUser(String id) throws DbException, DomainException {
        return getUserRepository().get(id);
    }

    public List<User> getUsers() throws DbException, DomainException {
        return this.personDb.getAll();
    }

    public void addUser(User user) throws DbException {
        getUserRepository().add(user);
    }

    public void updateUser(User user) throws DbException {
        getUserRepository().update(user);
    }

    public void deleteUser(String id) throws DbException {
        getUserRepository().delete(id);
    }

    private PersonRepository getUserRepository() {
        return personDb;
    }

    public boolean checkPassword(String password, String id) throws NoSuchAlgorithmException, UnsupportedEncodingException, DbException, DomainException {
        User u = getUser(id);
        return u.isCorrectPassword(password);
    }

    // PRODUCT

    public Product getProduct(String productId) throws DbException {
        return getProductRepository().get(productId);
    }

    public List<Product> getProducts() throws DbException {
        return getProductRepository().getAll();
    }

    public void addProduct(Product p) throws DbException {
        getProductRepository().add(p);
    }

    public void updateProduct(Product p) throws DbException {
        getProductRepository().update(p);
    }

    public void deleteProduct(String id) throws DbException {
        getProductRepository().delete(id);
    }

    private ProductDB getProductRepository() {
        return productDb;
    }
}