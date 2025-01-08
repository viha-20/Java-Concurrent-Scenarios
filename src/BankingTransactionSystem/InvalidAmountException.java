/**
 * @author    : K.A.H.D. Vihangi Devthilini Jayasekara
 * Date       : 2024.12.17
 * Time       : 10.30 AM
 * Student ID : 20211207 | w1898902
 * Module(2024)     : 6SENG006C.1 Concurrent Programming
 * */

package BankingTransactionSystem;

// Custom exception for invalid amount errors.
public class InvalidAmountException extends Throwable {
    public InvalidAmountException(String invalidAmountException) {
        super(invalidAmountException); // Call the superclass constructor with the error message.
    }
}
