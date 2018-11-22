package domain.model.shop;

import domain.model.DomainException;
import domain.model.Product;

public class ProductOrder {
    private Product product;
    private int quantity;

    public ProductOrder(Product product, int quantity) throws DomainException {
        setProduct(product);
        setQuantity(quantity);
    }

    public void setQuantity(int quantity) throws DomainException  {
        if (quantity <= 0) {
            throw new DomainException ("Quantity cannot be lower than or equal to 0.");
        }
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    private void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public String getProductDescription() {
        return getProduct().getDescription();
    }

    public double getProductPrice() {
        return getProduct().getPrice();
    }

    public String getProductId() {
        return getProduct().getProductId();
    }

    public void setPrice(double newPrice) {
        product.setPrice(newPrice);
    }
}
