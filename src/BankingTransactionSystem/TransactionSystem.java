/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package BankingTransactionSystem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Manages transactions between multiple bank accounts.
public class TransactionSystem {
    private final Map<Integer, BankAccount> accounts = new HashMap<>(); // Stores all accounts by their IDs.

    // Constructor initializes the system with a list of bank accounts.
    public TransactionSystem(List<BankAccount> accountList) {
        for (BankAccount account : accountList) {
            accounts.put(account.getAccountNumber(), account);
        }
    }

    // Transfers a specified amount between two accounts.
    public boolean transfer(int fromAccountId, int toAccountId, BigDecimal amount, int transactionId) {
        BankAccount fromAccount = accounts.get(fromAccountId); // Get the source account.
        BankAccount toAccount = accounts.get(toAccountId); // Get the destination account.

        BankAccount firstLock;
        BankAccount secondLock;

        // Lock accounts in consistent order based on their IDs to avoid deadlocks.
        if (fromAccountId < toAccountId) {
            firstLock = fromAccount;
            secondLock = toAccount;
        } else {
            firstLock = toAccount;
            secondLock = fromAccount;
        }

        // Acquire locks on both accounts.
        firstLock.lock();
        secondLock.lock();
        try {
            System.out.println("Withdrawing from (Account ID): " + fromAccountId);
            fromAccount.withDraw(amount, transactionId); // Perform the withdrawal.
            System.out.println("Deposited to (Account ID): " + toAccountId);
            toAccount.deposit(amount, transactionId); // Perform the deposit.
            System.out.println("Transaction successful: $" + amount + " from Account " + fromAccountId + " to Account " + toAccountId +
                    " | From Account New Balance: " + fromAccount.getBalance() + " To Account New Balance: " + toAccount.getBalance());
            return true; // Indicate success.
        } catch (InsufficientBalanceException | InvalidAmountException e) {
            System.out.println("Transaction failed: " + e.getMessage());
            return false; // Indicate failure.
        } finally {
            secondLock.unlock(); // Release locks in reverse order.
            firstLock.unlock();
        }
    }

    // Reverses a failed transaction.
    public void reverseTransaction(int fromAccountId, int toAccountId, BigDecimal amount, int transactionId) {
        System.out.println("Reversing transaction...");
        transfer(toAccountId, fromAccountId, amount, transactionId); // Perform the reversal.
    }

    // Prints the balances of all accounts.
    public void printAccountBalances() {
        accounts.values().forEach(account ->
                System.out.println("Account " + account.getAccountNumber() + ": $" + account.getBalance())
        );
    }
}
