package ecommerce;

import java.time.LocalDate;

/**
 * Main class to demonstrate the e-commerce system functionality
 */
public class Main {
    public static void main(String[] args) {
        // Create products
        Cheese cheese = new Cheese("Cheese", 100, 10, LocalDate.now().plusDays(7), 0.2); // 200g
        Biscuits biscuits = new Biscuits("Biscuits", 150, 5, LocalDate.now().plusDays(30), 0.7); // 700g
        TV tv = new TV("TV", 500, 3, 15.0); // 15kg
        Mobile mobile = new Mobile("Mobile", 800, 8, 0.2); // 200g
        ScratchCard scratchCard = new ScratchCard("Mobile Scratch Card", 25, 100);
        
        // Create customer with sufficient balance
        Customer customer = new Customer("John Doe", 2000);
        
        // Create cart and add products
        Cart cart = new Cart();
        
        System.out.println("=== Test Case 1: Normal Checkout ===");
        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);
            
            ECommerceSystem ecommerce = new ECommerceSystem();
            ecommerce.checkout(customer, cart);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 2: TV Purchase ===");
        try {
            cart.add(tv, 1);
            cart.add(mobile, 1);
            
            ECommerceSystem ecommerce = new ECommerceSystem();
            ecommerce.checkout(customer, cart);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 3: Empty Cart Error ===");
        try {
            Cart emptyCart = new Cart();
            ECommerceSystem ecommerce = new ECommerceSystem();
            ecommerce.checkout(customer, emptyCart);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 4: Insufficient Balance Error ===");
        try {
            Customer poorCustomer = new Customer("Poor Customer", 50);
            Cart expensiveCart = new Cart();
            expensiveCart.add(tv, 2);
            
            ECommerceSystem ecommerce = new ECommerceSystem();
            ecommerce.checkout(poorCustomer, expensiveCart);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 5: Out of Stock Error ===");
        try {
            Cart stockCart = new Cart();
            stockCart.add(cheese, 20); // More than available (10)
            
            ECommerceSystem ecommerce = new ECommerceSystem();
            ecommerce.checkout(customer, stockCart);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 6: Expired Product Error ===");
        try {
            Cheese expiredCheese = new Cheese("Expired Cheese", 100, 5, LocalDate.now().minusDays(1), 0.2);
            Cart expiredCart = new Cart();
            expiredCart.add(expiredCheese, 1);
            
            ECommerceSystem ecommerce = new ECommerceSystem();
            ecommerce.checkout(customer, expiredCart);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Final Customer Balance: " + customer.getBalance() + " ===");
    }
}