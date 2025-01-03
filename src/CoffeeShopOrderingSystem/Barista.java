package CoffeeShopOrderingSystem;

public class Barista implements Runnable{


    private  CoffeeShop coffeeShop;
    private  String order;

    public Barista(CoffeeShop coffeeShop){
        this.coffeeShop=coffeeShop;
        this.order=order;
    }

    @Override
    public void run() {
        order = coffeeShop.prepareOrder();
        System.out.println(Thread.currentThread().getName() + " completed order: " + order);
    }
}
