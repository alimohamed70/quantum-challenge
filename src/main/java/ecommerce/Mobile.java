package ecommerce;

/**
 * Mobile product - doesn't expire and requires shipping
 */
public class Mobile extends NonExpirableProduct implements Shippable {
    private double weight;
    
    public Mobile(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    
    @Override
    public boolean requiresShipping() {
        return true;
    }
    
    @Override
    public double getWeight() {
        return weight;
    }
}