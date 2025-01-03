package BankingTransactionSystem;

public class InvalidAmountException extends Throwable{
    public InvalidAmountException(String invalidAmountException){
        super(invalidAmountException);
    }

}
