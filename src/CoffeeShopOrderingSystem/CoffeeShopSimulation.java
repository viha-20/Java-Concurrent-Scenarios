package CoffeeShopOrderingSystem;

public class CoffeeShopSimulation {
    public static void main(String[] args) {

        Thread[] customerNBarista = new Thread[20];
        CoffeeShop coffeeShop = new CoffeeShop(2);

        for (int i=0;i<20;i++){
            customerNBarista[i] =  new Thread(new Customer(coffeeShop,"Customer " + i));
            customerNBarista[++i] = new Thread(new Barista(coffeeShop),"Barista " + i);
        }

        // Start all threads
        for (Thread barista0Customer:customerNBarista){
            barista0Customer.start();
        }


    }
}