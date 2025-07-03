package ecommerce;

import java.util.ArrayList;
import java.util.List;

/**
 * Main e-commerce system for handling checkout operations
 */
public class ECommerceSystem {
    private ShippingService shippingService;
    
    public ECommerceSystem() {
        this.shippingService = new ShippingService();
    }
    
    public void checkout(Customer customer, Cart cart) {
        // Validate checkout preconditions
        validateCheckout(customer, cart);
        
        // Calculate totals
        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = getShippableItems(cart);
        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;
        
        // Process payment
        customer.deductBalance(totalAmount);
        
        // Update product quantities
        updateProductQuantities(cart);
        
        // Process shipment if needed
        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }
        
        // Print checkout receipt
        printCheckoutReceipt(cart, subtotal, shippingFee, totalAmount, customer.getBalance());
        
        // Clear cart after successful checkout
        cart.clear();
    }
    
    private void validateCheckout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        
        // Check for expired products and stock availability
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            
            if (product.isExpired()) {
                throw new IllegalStateException("Product is expired: " + product.getName());
            }
            
            if (!product.isAvailable(item.getQuantity())) {
                throw new IllegalStateException("Product is out of stock: " + product.getName());
            }
        }
        
        // Check customer balance
        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = getShippableItems(cart);
        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;
        
        if (!customer.hasBalance(totalAmount)) {
            throw new IllegalStateException("Customer's balance is insufficient");
        }
    }
    
    private List<Shippable> getShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.requiresShipping() && product instanceof Shippable) {
                // Add multiple instances for quantity > 1
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        
        return shippableItems;
    }
    
    private void updateProductQuantities(Cart cart) {
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
    }
    
    private void printCheckoutReceipt(Cart cart, double subtotal, double shippingFee, double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");
        
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f%n", 
                item.getQuantity(), 
                item.getProduct().getName(), 
                item.getTotalPrice());
        }
        
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        
        if (shippingFee > 0) {
            System.out.printf("Shipping %.0f%n", shippingFee);
        }
        
        System.out.printf("Amount %.0f%n", totalAmount);
        System.out.printf("Customer balance after payment: %.0f%n", remainingBalance);
    }
}