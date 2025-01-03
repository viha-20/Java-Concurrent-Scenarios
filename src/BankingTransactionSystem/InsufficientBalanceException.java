package BankingTransactionSystem;

public class InsufficientBalanceException extends Throwable {
    public InsufficientBalanceException(String insufficientFundsInTheAccount) {
        super(insufficientFundsInTheAccount);
    }

}
