package domain.model.shop;

import domain.model.DomainException;
import domain.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Cart {
    private Date creationDate;
    private List<ProductOrder> productsOrdered = new ArrayList<>();

    public List<ProductOrder> getProductsOrdered() {
        return productsOrdered;
    }

    public Cart() {
        this.creationDate = new Date();
    }

    public void addProduct(Product product, int quantity) throws DomainException {
        ProductOrder order = new ProductOrder(product);
        productsOrdered.add(order);
    }

    public ProductOrder getOrder(String id) {
        for(ProductOrder order : productsOrdered){
            if(order.getProductId().equals(id)){
                return order;
            }
        }
        return null;
    }



    public void deleteProduct(String productId) {
        ProductOrder order = getOrder(productId);
        productsOrdered.remove(order);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getTotalPrice() {
        double total = 0;
        Collection<ProductOrder> orders = productsOrdered;
        for(ProductOrder order : orders){
            total += order.getProductPrice();
        }
        return total;
    }


    public int getNumberOfProductOrdersInCart() {
        return productsOrdered.size();
    }

}
