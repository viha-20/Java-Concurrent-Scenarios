/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package CoffeeShopOrderingSystem;

// Represents a Customer thread responsible for placing coffee orders.
public class Customer implements Runnable{

    private CoffeeShop coffeeShop; // Reference to the shared CoffeeShop instance.
    private String order; // The order to be placed.

    // Constructor to initialize the customer with a reference to the coffee shop and their order.
    public Customer(CoffeeShop coffeeShop,String order){
        this.coffeeShop=coffeeShop;
        this.order=order;
    }

    @Override
    public void run() {
        // Place an order by adding it to the CoffeeShop queue.
        coffeeShop.placeOrder(order);
        try {
            // Simulate a delay before the next action.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
