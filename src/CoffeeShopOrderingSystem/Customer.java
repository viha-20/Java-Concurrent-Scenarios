package CoffeeShopOrderingSystem;

public class Customer implements Runnable{

    private  CoffeeShop coffeeShop;
    private  String order;

    public Customer(CoffeeShop coffeeShop,String order){
        this.coffeeShop=coffeeShop;
        this.order=order;
    }

    @Override
    public void run(){
        coffeeShop.placeOrder(order);
        System.out.println(Thread.currentThread().getName() + " successfully placed order: " + order);
    }

}
