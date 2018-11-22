package domain.db;

import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDBSQL implements ProductDB {
    private Properties properties ;
    private String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX38";
    private String currentSchema;
    public ProductDBSQL(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
            this.currentSchema = properties.getProperty("currentSchema");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Product product) {
        if (product == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = "INSERT INTO product (name, description, price) VALUES ('" + product.getName() + "', "
                + product.getDescription() + ", " + product.getPrice() + ")";
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<Product>();
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery("SELECT * FROM product");
            while (result.next()) {
                String name = result.getString("name");
                String description = result.getString("description");
                double price = Double.parseDouble(result.getString("price"));
                Product product = new Product(name, description, price);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return products;
    }
}
