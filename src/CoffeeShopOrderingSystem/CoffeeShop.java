package CoffeeShopOrderingSystem;

import java.util.LinkedList;

public class CoffeeShop { // Monitor

    private int capacity;
    private LinkedList<String> orderQueue = new LinkedList<>();

    public CoffeeShop(int capacity){
        this.capacity=capacity;
    }

    // producer : called by the customer
    public synchronized void placeOrder(String order){
        while (orderQueue.size()==capacity){
            try {
                System.out.println("The Order Queue is Full... Customer is waiting..");
                System.out.println(Thread.currentThread().getName() + " is waiting to place order: " + order);
                wait(); // wait if the queue is Full
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Customer was interrupted while waiting to place order. ");
            }
        }
        orderQueue.add(order);
        System.out.println(Thread.currentThread().getName() + " placed order: " + order);
        notifyAll();
    }

    // Consumer :  called by barista
    public synchronized String prepareOrder(){
        while (orderQueue.isEmpty()){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for orders");
                wait();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Barista was interrupt while waiting for orders.");
            }
        }
        String order = orderQueue.poll();
        System.out.println(Thread.currentThread().getName() + " is preparing order : " + order);
        notifyAll();
        return order;
    }
}
