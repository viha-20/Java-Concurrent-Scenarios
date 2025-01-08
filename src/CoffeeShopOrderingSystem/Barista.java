/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package CoffeeShopOrderingSystem;

// Represents a Barista thread responsible for preparing coffee orders.
public class Barista implements Runnable{

    private  CoffeeShop coffeeShop; // Reference to the shared CoffeeShop instance.
    private  String order; // Placeholder for the order being prepared.

    public Barista(CoffeeShop coffeeShop){ // Constructor to initialize the barista with a reference to the coffee shop.
        this.coffeeShop=coffeeShop;
        this.order=order;
    }

    @Override
    public void run() {
        order = coffeeShop.prepareOrder(); // Prepare an order by retrieving it from the CoffeeShop queue.
        try {
            Thread.sleep(1000); // Simulate time taken to prepare the coffee.
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        //System.out.println(Thread.currentThread().getName() + " successfully completed order: " + order);

    }
}
