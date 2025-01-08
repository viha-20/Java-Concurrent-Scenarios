/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package BankingTransactionSystem;

import java.math.BigDecimal;
import java.util.Arrays;

// Simulates concurrent banking transactions.
public class BankingTransactionSimulator {
    public static void main(String[] args) {
        // Create three bank accounts with initial balances.
        BankAccount account1 = new BankAccount(1, new BigDecimal("500.00"));
        BankAccount account2 = new BankAccount(2, new BigDecimal("1000.00"));
        BankAccount account3 = new BankAccount(3, new BigDecimal("1500.00"));

        // Create a transaction system with the accounts.
        TransactionSystem transactionSystem = new TransactionSystem(Arrays.asList(account1, account2, account3));

        // Simulate concurrent transactions using threads.
        Thread t1 = new Thread(() -> transactionSystem.transfer(1, 2, new BigDecimal("100.00"), 1), "Thread-1");
        Thread t2 = new Thread(() -> transactionSystem.transfer(2, 3, new BigDecimal("200.00"), 2), "Thread-2");
        Thread t3 = new Thread(() -> {
            // Intentionally trigger a reversal for an invalid transaction.
            if (!transactionSystem.transfer(3, 1, new BigDecimal("2000.00"), 3)) {
                System.out.println("Reversing the failed transaction...");
                transactionSystem.reverseTransaction(1, 3, new BigDecimal("2000.00"), 3);
            }
        }, "Thread-3");

        // Simulate concurrent read operations.
        Thread t4 = new Thread(() -> {
            System.out.println("Balance of Account 1: $" + account1.getBalance());
            System.out.println("Balance of Account 3: $" + account3.getBalance());
        }, "Thread-4");

        // Start all threads.
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            Thread.sleep(1000); // Pause to allow threads to finish their work.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wait for all threads to complete.
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final account balances.
        transactionSystem.printAccountBalances();
    }
}

