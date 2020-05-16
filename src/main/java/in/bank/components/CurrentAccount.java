package in.bank.components;

import in.bank.constants.AccountType;
import in.bank.constants.TransactionType;
import in.bank.exception.AmountLessThanOneException;
import in.bank.exception.CurrentBalanceWillBeLessThanMinimumBalanceException;
import in.bank.exception.InvalidAccountNoException;
import in.bank.exception.WithdrawAmountGreaterThanBalanceException;
import org.springframework.stereotype.Component;

@Component
public class CurrentAccount extends BankAccount {
    Double minimumBalance = 20000.0;
    Double interestRate = 0.0;

    public CurrentAccount(Double amount, String accountNo) throws AmountLessThanOneException, InvalidAccountNoException {
        if(amount == null || amount < 1) {
            throw new AmountLessThanOneException();
        } else {
            super.currentBalance = amount;
        }
        if(accountNo == null || accountNo.length() == 0) {
            throw new InvalidAccountNoException();
        }
        super.accountNumber = accountNo;
    }

    public Double getMinimumBalance() {
        return minimumBalance;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void withdraw(Double amount) throws WithdrawAmountGreaterThanBalanceException,
            CurrentBalanceWillBeLessThanMinimumBalanceException, AmountLessThanOneException {
        if(amount == null || amount < 1) {
            throw new AmountLessThanOneException();
        }
        if(amount > this.currentBalance) {
            throw new WithdrawAmountGreaterThanBalanceException();
        } else if((this.currentBalance - amount) < minimumBalance) {
            throw new CurrentBalanceWillBeLessThanMinimumBalanceException();
        } else {
            currentBalance = currentBalance - amount;
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setType(TransactionType.DR.name());
            transaction.setTransactionId(String.valueOf(super.transactions.size() + 1));
            super.transactions.add(transaction);
        }
    }

    public String getTpe() {
        return AccountType.CURRENT.name();
    }


}
