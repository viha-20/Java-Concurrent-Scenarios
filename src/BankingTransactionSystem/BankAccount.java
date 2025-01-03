package BankingTransactionSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);

    private Lock readLock = reentrantReadWriteLock.readLock();
    private Lock writeLock = reentrantReadWriteLock.writeLock();
    private List<String> transactions = new ArrayList<>();

    private BigDecimal balance;
    private int accountNumber;

    public BankAccount(int accountNumber,BigDecimal initialBalance){
        if (initialBalance.doubleValue() < 0) {
            throw new IllegalArgumentException("Initial balance must be non-negative.");
        }
        this.accountNumber=accountNumber;
        this.balance=initialBalance;
    }

    public BigDecimal getBalance(){
        readLock.lock();
        try {
            return balance;
        }
        finally {
            readLock.unlock();
        }
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public void lock() {
        writeLock.lock();
    }

    public void unlock() {
        writeLock.unlock();
    }

    public void deposit(BigDecimal amount, int transactionId) throws InvalidAmountException{

        if (amount.doubleValue()<=0){
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        writeLock.lock();
        try {
            balance = balance.add(amount);
            transactions.add(Thread.currentThread().getName() + " Deposit: " + amount + " | Transaction ID: " + transactionId);
            System.out.println(Thread.currentThread().getName() + " Deposit successfully. ");
            System.out.println(Thread.currentThread().getName() + " Balance After Deposit: " + balance.doubleValue());
        }
        finally {
            writeLock.unlock();
        }
    }

    public void withDraw(BigDecimal amount,int transactionId) throws InsufficientBalanceException {

        if (amount.doubleValue()<=0){
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        writeLock.lock();
        try {
            if (balance.compareTo(amount) < 0){
                throw new InsufficientBalanceException("Insufficient funds for transaction ID: " + transactionId);
            }
            balance = balance.subtract(amount);
            transactions.add(Thread.currentThread().getName() + " Withdrawal: " + amount + " | Transaction ID: " + transactionId);
            System.out.println(Thread.currentThread().getName() + " Withdrawal successful.");
            System.out.println(Thread.currentThread().getName() + " Balance After Withdrawal: " + balance.doubleValue());
        }
        finally {
            writeLock.unlock();
        }
    }
}

