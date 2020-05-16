package in.bank.components;

import in.bank.constants.TransactionType;
import in.bank.exception.AmountLessThanOneException;
import in.bank.exception.CurrentBalanceWillBeLessThanMinimumBalanceException;
import in.bank.exception.WithdrawAmountGreaterThanBalanceException;

import java.util.LinkedList;
import java.util.List;

public abstract class BankAccount {
    String accountNumber;
    Double currentBalance;
    List<Transaction> transactions = new LinkedList<>();

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void depsoit(Double amount) throws AmountLessThanOneException {
        if(amount == null || amount  < 1) {
            throw new AmountLessThanOneException();
        } else {
            this.currentBalance = this.currentBalance + amount;
            Transaction transaction = new Transaction();
            transaction.setType(TransactionType.CR.name());
            transaction.setAmount(amount);
            transaction.setTransactionId(String.valueOf(transactions.size() + 1));
            this.transactions.add(transaction);
        }

    }

    public abstract void withdraw(Double amount) throws WithdrawAmountGreaterThanBalanceException,
            CurrentBalanceWillBeLessThanMinimumBalanceException, AmountLessThanOneException;

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    public abstract Double getMinimumBalance();

    public abstract Double getInterestRate();

    public List<Transaction> voidGetMiniStatement() {
        return transactions.subList(Math.max(transactions.size() - 10, 0), transactions.size());
    }

    public abstract String getTpe();
}
