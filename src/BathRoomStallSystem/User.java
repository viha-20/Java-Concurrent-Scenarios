package BathRoomStallSystem;

import java.util.Random;

public class User implements Runnable {

    private final int userId;
    private final UserType userType;
    private int stallNumber;

    public User(int userId) {
        this.userId = userId;
        // Assign Employee or Student based on the user ID
        this.userType = (userId % 2 == 0) ? UserType.EMPLOYEE : UserType.STUDENT;
        // Randomly assign a stall number between 1 and 6
        this.stallNumber = (new Random()).nextInt(6) + 1;
    }

    @Override
    public void run() {
        try {
            // Wait for a stall to be available
            System.out.println(userType + " " + userId + " is waiting for a stall.");

            // Acquire a stall using StallManager
            StallManager.acquireStall(this);

            // Simulate using the stall for a random period
            Thread.sleep((long) (Math.random() * 1000));

            // Release the stall once done using it
            StallManager.releaseStall(this);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Getter for userId
    public int getUserId() {
        return userId;
    }

    // Getter for userType
    public UserType getUserType() {
        return userType;
    }

    // Getter for stallNumber
    public int getStallNumber() {
        return stallNumber;
    }

    // Setter for stallNumber
    public void setStallNumber(int stallNumber) {
        this.stallNumber = stallNumber;
    }
}
