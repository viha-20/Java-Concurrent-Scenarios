package BathRoomStallSystem;

/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */
import java.util.concurrent.Semaphore;

public class BathroomStalls {

        // Define the number of bathroom stalls
        private static final int NUM_STALLS = 6;

        // Create a Semaphore to manage access to the stalls
        private static final Semaphore stalls = new Semaphore(NUM_STALLS, true);

        // Array to track which stalls are in use
        private static final boolean[] stallUsage = new boolean[NUM_STALLS];

        // Total number of employees and students
        private static final int NUM_USERS = 100;

        public static void main(String[] args) {
            // Simulate users accessing the bathroom
            for (int i = 1; i <= NUM_USERS; i++) {
                String userType = (i % 2 == 0) ? "Employee" : "Student";
                new Thread(new BathroomUser(i, userType)).start();
            }
        }

        static class BathroomUser implements Runnable {
            private final int id;
            private final String userType;

            public BathroomUser(int id, String userType) {
                this.id = id;
                this.userType = userType;
            }

            @Override
            public void run() {
                int stallNumber = -1;
                try {
                    System.out.println(userType + " " + id + " is waiting to use a stall.");

                    // Acquire a stall
                    stalls.acquire();

                    synchronized (stallUsage) {
                        for (int i = 0; i < NUM_STALLS; i++) {
                            if (!stallUsage[i]) {
                                stallUsage[i] = true;
                                stallNumber = i + 1;
                                break;
                            }
                        }
                    }

                    System.out.println(userType + " " + id + " is using stall " + stallNumber + ".");

                    // Simulate time spent in the stall
                    Thread.sleep((long) (Math.random() * 3000) + 1000);

                    System.out.println(userType + " " + id + " is leaving stall " + stallNumber + ".");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(userType + " " + id + " was interrupted.");

                } finally {
                    if (stallNumber != -1) {
                        synchronized (stallUsage) {
                            stallUsage[stallNumber - 1] = false;
                        }
                    }
                    // Release the stall for others to use
                    stalls.release();
                }
            }
        }
    }



