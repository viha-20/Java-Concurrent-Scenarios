package BathRoomStallSystem;

import java.util.concurrent.*;

public class StallManager {

    // Semaphore to manage 6 bathroom stalls, initially all are available
    private static final Semaphore stalls = new Semaphore(6, true); // 6 available stalls

    // Method to acquire a stall
    public static void acquireStall(User user) throws InterruptedException {
        // Acquire a stall (this will block if all stalls are occupied)
        stalls.acquire();
        int stallNumber = user.getStallNumber();
        System.out.println(user.getUserType() + " " + user.getUserId() + " is using stall " + stallNumber);
    }

    // Method to release a stall
    public static void releaseStall(User user) throws InterruptedException {
        int stallNumber = user.getStallNumber();
        stalls.release(); // Release a stall
        System.out.println(user.getUserType() + " " + user.getUserId() + " has finished using stall " + stallNumber);
    }
}


