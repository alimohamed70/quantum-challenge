package ecommerce;

import java.time.LocalDate;

/**
 * Abstract base class for all products in the e-commerce system
 */
public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }
    
    public void reduceQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot reduce quantity by more than available stock");
        }
        this.quantity -= amount;
    }
    
    // Abstract method to check if product is expired
    public abstract boolean isExpired();
    
    // Abstract method to check if product requires shipping
    public abstract boolean requiresShipping();
}