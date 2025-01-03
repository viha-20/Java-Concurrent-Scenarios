//package BankingTransactionSystem;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//
//public class BankingTransactionSimulator {
//    public static void main(String[] args) {
//
//        // Create bank accounts
//        BankAccount account1 = new BankAccount(1, new BigDecimal("500.00"));
//        BankAccount account2 = new BankAccount(2, new BigDecimal("1000.00"));
//        BankAccount account3 = new BankAccount(3, new BigDecimal("1500.00"));
//
//        TransactionSystem transactionSystem = new TransactionSystem(Arrays.asList(account1, account2, account3));
//
//        // Simulate concurrent transactions
//        Thread t1 = new Thread(() -> transactionSystem.transfer(1, 2, new BigDecimal("100.00"), 1), "Thread-1");
//        Thread t2 = new Thread(() -> transactionSystem.transfer(2, 3, new BigDecimal("200.00"), 2), "Thread-2");
//        Thread t3 = new Thread(() -> transactionSystem.transfer(3, 1, new BigDecimal("50.00"), 3), "Thread-3");
//        Thread t4 = new Thread(() -> {
//            System.out.println("Balance of Account 1: $" + account1.getBalance());
//            System.out.println("Balance of Account 3: $" + account3.getBalance());
//        }, "Thread-4");
//
//        // Start threads
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//
//        // Wait for threads to finish
//        try {
//            t1.join();
//            t2.join();
//            t3.join();
//            t4.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Print final balances
//        transactionSystem.printAccountBalances();
//
//
//
//
//    }
//}


package BankingTransactionSystem;

import java.math.BigDecimal;
import java.util.Arrays;

public class BankingTransactionSimulator {
    public static void main(String[] args) {
        // Create bank accounts
        BankAccount account1 = new BankAccount(1, new BigDecimal("500.00"));
        BankAccount account2 = new BankAccount(2, new BigDecimal("1000.00"));
        BankAccount account3 = new BankAccount(3, new BigDecimal("1500.00"));

        TransactionSystem transactionSystem = new TransactionSystem(Arrays.asList(account1, account2, account3));

        // Simulate concurrent transactions
        Thread t1 = new Thread(() -> transactionSystem.transfer(1, 2, new BigDecimal("100.00"), 1), "Thread-1");
        Thread t2 = new Thread(() -> transactionSystem.transfer(2, 3, new BigDecimal("200.00"), 2), "Thread-2");
        Thread t3 = new Thread(() -> {
            // Intentionally trigger a reversal
            if (!transactionSystem.transfer(3, 1, new BigDecimal("2000.00"), 3)) {
                System.out.println("Reversing the failed transaction...");
                transactionSystem.reverseTransaction(1, 3, new BigDecimal("2000.00"), 3);
            }
        }, "Thread-3");

        // Concurrent read operation
        Thread t4 = new Thread(() -> {
            System.out.println("Balance of Account 1: $" + account1.getBalance());
            System.out.println("Balance of Account 3: $" + account3.getBalance());
        }, "Thread-4");

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final balances
        transactionSystem.printAccountBalances();
    }
}
