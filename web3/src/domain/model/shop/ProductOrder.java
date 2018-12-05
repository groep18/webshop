package domain.model.shop;

import domain.model.DomainException;
import domain.model.Product;

public class ProductOrder {
    private Product product;

    public ProductOrder(Product product) throws DomainException {
        setProduct(product);
    }



    public Product getProduct() {
        return product;
    }

    private void setProduct(Product product) {
        this.product = product;
    }


    public String getProductDescription() {
        return getProduct().getDescription();
    }

    public double getProductPrice() {
        return getProduct().getPrice();
    }

    public String getProductId() {
        return getProduct().getId();
    }

}
