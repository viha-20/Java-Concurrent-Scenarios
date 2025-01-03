package BankingTransactionSystem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionSystem {
    private final Map<Integer, BankAccount> accounts = new HashMap<>();

    public TransactionSystem(List<BankAccount> accountList) {
        for (BankAccount account : accountList) {
            accounts.put(account.getAccountNumber(), account);
        }
    }

    public boolean transfer(int fromAccountId, int toAccountId, BigDecimal amount, int transactionId) {
        BankAccount fromAccount = accounts.get(fromAccountId);
        BankAccount toAccount = accounts.get(toAccountId);

        BankAccount firstLock;
        BankAccount secondLock;

        // Lock accounts in consistent order to avoid deadlocks
        if (fromAccountId < toAccountId) {
            firstLock = fromAccount;
            secondLock = toAccount;
        } else {
            firstLock = toAccount;
            secondLock = fromAccount;
        }

        firstLock.lock();
        secondLock.lock();
        try {
            fromAccount.withDraw(amount, transactionId);
            toAccount.deposit(amount, transactionId);
            System.out.println("Transaction successful: $" + amount + " from Account " + fromAccountId + " to Account " + toAccountId);
            return true;
        } catch (InsufficientBalanceException | InvalidAmountException e) {
            System.out.println("Transaction failed: " + e.getMessage());
            return false;
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }
    }

    public void reverseTransaction(int fromAccountId, int toAccountId, BigDecimal amount, int transactionId) {
        System.out.println("Reversing transaction...");
        transfer(toAccountId, fromAccountId, amount, transactionId);
    }

    public void printAccountBalances() {
        accounts.values().forEach(account ->
                System.out.println("Account " + account.getAccountNumber() + ": $" + account.getBalance())
        );
    }
}
