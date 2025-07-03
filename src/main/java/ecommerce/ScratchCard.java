package ecommerce;

/**
 * Scratch Card product - doesn't expire and doesn't require shipping (digital)
 */
public class ScratchCard extends NonExpirableProduct {
    
    public ScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }
    
    @Override
    public boolean requiresShipping() {
        return false;
    }
}