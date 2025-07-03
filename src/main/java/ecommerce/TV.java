package ecommerce;

/**
 * TV product - doesn't expire but requires shipping
 */
public class TV extends NonExpirableProduct implements Shippable {
    private double weight;
    
    public TV(String name, double price, int quantity, double weight) {
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