/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package CoffeeShopOrderingSystem;

import java.util.LinkedList;

// The CoffeeShop class acts as a monitor for the shared resource (order queue).
public class CoffeeShop { // Monitor

    private int capacity; // Maximum number of orders the queue can hold at any given time.
    private LinkedList<String> orderQueue = new LinkedList<>(); // Shared resource: Queue to hold coffee orders

    public CoffeeShop(int capacity){ // Constructor initializes the coffee shop with the specified capacity.
        this.capacity=capacity;
    }

    // Producer : called by the customer
    public synchronized void placeOrder(String order){ // Method for customers to place orders in the queue (Producer role).
        while (orderQueue.size()==capacity){ // If the queue is full, wait until space is available.
            try {
                System.out.println("The Order Queue is Full... Customer is waiting..");
                System.out.println(Thread.currentThread().getName() + " is waiting to place order: " + order);
                wait(); // wait if the queue is Full
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt(); // Restores the interrupted status of the thread.
                System.out.println("Customer was interrupted while waiting to place order. ");
            }
        }
        // Add the order to the queue once there is space.
        orderQueue.add(order);
        System.out.println(Thread.currentThread().getName() + " successfully placed order :  " + order);
        notifyAll(); // Notify waiting threads that the state of the queue has changed.
    }

    // Consumer :  called by barista
    public synchronized String prepareOrder(){  //Method for baristas to prepare orders from the queue (Consumer role).
        while (orderQueue.isEmpty()){ // If the queue is empty, wait until there is an order to process.
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for orders");
                wait(); // Causes the current thread to wait until notified.
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt(); // Restores the interrupted status of the thread.
                System.out.println("Barista was interrupt while waiting for orders.");
            }
        }
        // Remove an order from the queue to process it.
        String order = orderQueue.poll();
        System.out.println(Thread.currentThread().getName() + " successfully prepared order : " + order);
        notifyAll(); // Notify waiting threads that the state of the queue has changed.
        return order;
    }
}
