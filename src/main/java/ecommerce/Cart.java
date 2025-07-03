package ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Shopping cart implementation
 */
public class Cart {
    private List<CartItem> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
        }
        
        if (product.isExpired()) {
            throw new IllegalArgumentException("Product is expired: " + product.getName());
        }
        
        // Check if product already exists in cart
        Optional<CartItem> existingItem = items.stream()
            .filter(item -> item.getProduct().equals(product))
            .findFirst();
            
        if (existingItem.isPresent()) {
            int newQuantity = existingItem.get().getQuantity() + quantity;
            if (!product.isAvailable(newQuantity)) {
                throw new IllegalArgumentException("Insufficient stock for total quantity of product: " + product.getName());
            }
            existingItem.get().setQuantity(newQuantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
    }
    
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public double getSubtotal() {
        return items.stream()
            .mapToDouble(CartItem::getTotalPrice)
            .sum();
    }
    
    public void clear() {
        items.clear();
    }
}