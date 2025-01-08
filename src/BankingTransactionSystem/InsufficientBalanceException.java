/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package BankingTransactionSystem;

// Custom exception for insufficient balance errors.
public class InsufficientBalanceException extends Throwable {
    public InsufficientBalanceException(String insufficientFundsInTheAccount) {
        super(insufficientFundsInTheAccount); // Call the superclass constructor with the error message.
    }
}

