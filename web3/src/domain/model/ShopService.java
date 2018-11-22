package domain.model;

import domain.db.*;

import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDBSQL personDb;
    private ProductDBSQL productDb;

    public ShopService(Properties properties) {
        personDb = new PersonDBSQL(properties);
        productDb = new ProductDBSQL(properties);
    }
    //Product
    public List<Product> getProducts() {
        return productDb.getAll();
    }
    public void addProduct(Product product) {
        productDb.add(product);
    }
    public ProductDB getProductDB() {
        return this.productDb;
    }
    //Person
    public List<Person> getPersons() {
        return personDb.getAll();
    }

    public void addPerson(Person person) {
        personDb.add(person);
    }
    public PersonRepository getPersonDb() {
        return this.personDb;
    }
}