/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package BankingTransactionSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount { // Represents a bank account with concurrency control mechanisms.


    // ReentrantReadWriteLock allows fine-grained control over read and write operations.
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);

    // Separate locks for read and write operations.
    private Lock readLock = reentrantReadWriteLock.readLock();
    private Lock writeLock = reentrantReadWriteLock.writeLock();

    private List<String> transactions = new ArrayList<>(); // List to store transaction history.
    private BigDecimal balance; // Account balance.
    private int accountNumber; // Unique account number.


    // Constructor initializes the account with an account number and an initial balance.
    public BankAccount(int accountNumber, BigDecimal initialBalance) {
        if (initialBalance.doubleValue() < 0) { // Ensure non-negative balance.
            throw new IllegalArgumentException("Initial balance must be non-negative.");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Retrieves the account balance with a read lock.
    public BigDecimal getBalance() {
        readLock.lock(); // Acquire the read lock for safe concurrent access.
        try {
            return balance;
        } finally {
            readLock.unlock(); // Release the read lock.
        }
    }

    // Retrieves the account number.
    public int getAccountNumber() {
        return accountNumber;
    }

    // Acquires the write lock for operations that modify the account.
    public void lock() {
        writeLock.lock();
    }

    // Releases the write lock after modifications.
    public void unlock() {
        writeLock.unlock();
    }


    // Deposits a specified amount into the account.
    public void deposit(BigDecimal amount, int transactionId) throws InvalidAmountException {
        if (amount.doubleValue() <= 0) { // Ensure deposit amount is positive.
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        writeLock.lock(); // Acquire the write lock to ensure exclusive access.
        try {
            balance = balance.add(amount); // Update the balance.
            // Add the transaction to the history log.
            transactions.add(Thread.currentThread().getName() + " Deposit: " + amount + " | Transaction ID: " + transactionId);
            System.out.println(Thread.currentThread().getName() + " Deposit successful. Balance After Deposit: " + balance.doubleValue() + " - Account " + this.accountNumber);
        } finally {
            writeLock.unlock(); // Release the write lock.
        }
    }

    // Withdraws a specified amount from the account.
    public void withDraw(BigDecimal amount, int transactionId) throws InsufficientBalanceException {
        if (amount.doubleValue() <= 0) { // Ensure withdrawal amount is positive.
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        writeLock.lock(); // Acquire the write lock to ensure exclusive access.
        try {
            // Check if there are sufficient funds for the withdrawal.
            if (balance.compareTo(amount) < 0) {
                throw new InsufficientBalanceException("Insufficient funds for transaction ID: " + transactionId);
            }
            balance = balance.subtract(amount); // Update the balance.
            // Add the transaction to the history log.
            transactions.add(Thread.currentThread().getName() + " Withdrawal: " + amount + " | Transaction ID: " + transactionId);
            System.out.println(Thread.currentThread().getName() + " Withdrawal successful. Balance After Withdrawal: " + balance.doubleValue() + " - Account " + transactionId);
        } finally {
            writeLock.unlock(); // Release the write lock.
        }
    }
}

