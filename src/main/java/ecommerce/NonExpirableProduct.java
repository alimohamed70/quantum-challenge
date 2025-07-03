package ecommerce;

/**
 * Abstract class for products that don't expire
 */
public abstract class NonExpirableProduct extends Product {
    
    public NonExpirableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }
    
    @Override
    public boolean isExpired() {
        return false;
    }
}