package domain.db;

import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDBSQL implements ProductDB {
    private PreparedStatement statement;
    private Connection connection;
    private Properties properties;
    private String url;
    private String schema;

    public ProductDBSQL(Properties properties) throws DbException {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
            this.schema = properties.getProperty("currentSchema");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }


    @Override
    public Product get(String id) throws DbException {

        try {
            String sql;
            sql = "SELECT * FROM " + schema + ".product "
                    + "WHERE productnummer = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet result;
            result = statement.executeQuery();

            if(!result.isBeforeFirst()) {
                throw new DbException("No product with given ID found.");
            }

            result.next();

            String productdescription = result.getString("productdescription");
            double productprice = Double.parseDouble(result.getString("productprice"));
            String productname = result.getString("productname");

            Product p = new Product(id, productname, productdescription, productprice);

            return p;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() throws DbException {
        List products = new ArrayList<Product>();
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement();){
            ResultSet result = statement.executeQuery("SELECT * FROM " + schema + ".product ORDER BY productnummer ASC");

            while(result.next()) {
                String id = result.getString("productnummer");
                String productdescription = result.getString("productdescription");
                double productprice = Double.parseDouble(result.getString("productprice"));
                String productname = result.getString("productname");


                Product p = new Product(id, productname,productdescription,productprice);
                products.add(p);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return products;
    }

    @Override
    public void add(Product product) throws DbException {
        if(product == null) {
            throw new DbException("No product to add");
        }

        try {
            String sql;
            sql = "SELECT * FROM " + schema + ".product "
                    + "WHERE productnummer = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, product.getId());

            ResultSet result = statement.executeQuery();

            if(result.isBeforeFirst()) {
                throw new DbException("Product already exists");
            }

            sql = "INSERT INTO " + schema + "product (productnummer, productname, productdescription, productprice)"
                    + "VALUES(?,?,?,?)";

            statement = connection.prepareStatement(sql);

            statement.setString(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Product product) throws DbException {
        if(product == null) {
            throw new DbException("No product to update");
        }


        try {
            String sql = "SELECT * FROM " + schema + ".product "
                    + "WHERE productnummer = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, product.getId());

            ResultSet result = statement.executeQuery();

            if(!result.isBeforeFirst()) {
                throw new DbException("No product with given ID found.");
            }

            sql = "UPDATE " + schema + ".product "
                    + "SET productdescription = ?, productprice = ?, productname "
                    + "WHERE productnummer = ?";

            statement = connection.prepareStatement(sql);

            statement.setString(1, product.getDescription());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(String productId) throws DbException {

        try {
            String sql = "SELECT * FROM " + schema + ".product "
                    + "WHERE productnummer = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, productId);

            ResultSet result = statement.executeQuery();

            if(!result.isBeforeFirst()) {
                throw new DbException("No product with given ID found.");
            }

            sql = "DELETE FROM " + schema + ".product "
                    + "WHERE productnummer = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, productId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

}
