package ecommerce;

import java.util.List;

/**
 * Service for handling shipping operations
 */
public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 10.0; // $10 per kg
    private static final double BASE_SHIPPING_FEE = 5.0; // Base fee of $5
    
    public void processShipment(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }
        
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        
        for (Shippable item : shippableItems) {
            double itemWeight = item.getWeight();
            totalWeight += itemWeight;
            System.out.printf("1x %s %.0fg%n", item.getName(), itemWeight * 1000);
        }
        
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
    
    public double calculateShippingFee(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            return 0.0;
        }
        
        double totalWeight = shippableItems.stream()
            .mapToDouble(Shippable::getWeight)
            .sum();
            
        return BASE_SHIPPING_FEE + (totalWeight * SHIPPING_RATE_PER_KG);
    }
}