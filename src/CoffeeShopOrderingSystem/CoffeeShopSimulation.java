/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package CoffeeShopOrderingSystem;

// Simulates the Coffee Shop system with customers and baristas.
public class CoffeeShopSimulation {

    public static void main(String[] args) {
        Thread[] customerNBarista = new Thread[100]; // Array to hold all customer and barista threads.
        CoffeeShop coffeeShop = new CoffeeShop(2); // Create a CoffeeShop instance with a queue capacity of 2.

        // Create 100 threads, alternating between customers and baristas.
        for (int i = 0; i < 100; i++) {
            customerNBarista[i] = new Thread(new Customer(coffeeShop, "Customer " + i)); // Create a customer thread.
            customerNBarista[++i] = new Thread(new Barista(coffeeShop), "Barista " + i); // Create a barista thread.
        }

        // Start all customer and barista threads.
        for (Thread baristaOrCustomer : customerNBarista) {
            baristaOrCustomer.start();
        }

        // Wait for all threads to complete their execution.
        try {
            for (Thread baristaOrCustomer : customerNBarista) {
                baristaOrCustomer.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
