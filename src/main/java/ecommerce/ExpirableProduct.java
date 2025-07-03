package ecommerce;

import java.time.LocalDate;

/**
 * Abstract class for products that can expire
 */
public abstract class ExpirableProduct extends Product {
    protected LocalDate expiryDate;
    
    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }
    
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
    
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}