package BathRoomStallSystem;

public class BathroomSimulation {
    public static void main(String[] args) {
        // Simulate 100 users (Employees and Students)
        for (int i = 0; i < 100; i++) {
            new Thread(new User(i)).start();
        }
    }
}
