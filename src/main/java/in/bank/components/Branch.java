package in.bank.components;

import in.bank.constants.AccountType;
import in.bank.exception.AmountLessThanOneException;
import in.bank.exception.InvalidAccountNoException;
import in.bank.exception.NoAccountFoundByIdException;
import in.bank.exception.NoCustomerFoundByPanException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Branch {
    private List<BankAccount> bankAccounts = new LinkedList<>();
    private List<Customer> customers = new LinkedList<>();
    private String branchId;

    public Branch(final String branchId) {
        this.branchId = branchId;
    }

    public BankAccount createBankAccount(String panNumber, String type, Double amount) throws AmountLessThanOneException, InvalidAccountNoException {
        BankAccount bankAccount = null;
        if(type.equalsIgnoreCase(AccountType.CURRENT.name())) {
            bankAccount = new CurrentAccount(amount, String.valueOf(bankAccounts.size() + 1));
        }
        if(type.equalsIgnoreCase(AccountType.SAVING.name())) {
            bankAccount = new SavingAccount(amount, String.valueOf(bankAccounts.size() + 1));
        }
        if(bankAccount != null) {
            Customer customer = new Customer(panNumber);
            customer.getAccounts().add(bankAccount);
            this.bankAccounts.add(bankAccount);
            this.customers.add(customer);
        }
        return bankAccount;
    }

    public Customer getCustomerByPanNumber(String panNumber) throws NoCustomerFoundByPanException {
        List<Customer> customersWithPan = this.customers.stream().filter(c -> c.getPanNumber().equalsIgnoreCase(panNumber)).collect(Collectors.toList());
        if(!customersWithPan.isEmpty()) {
            return customersWithPan.get(0);
        } else {
            throw new NoCustomerFoundByPanException();
        }
    }

    public BankAccount getAccountByAccountNo(String accountNo) throws NoAccountFoundByIdException {
        List<BankAccount> filteredAccount = this.bankAccounts.stream().filter(ba -> ba.getAccountNumber().equalsIgnoreCase(accountNo)).collect(Collectors.toList());
        if(!filteredAccount.isEmpty()) {
            return filteredAccount.get(0);
        } else {
            throw new NoAccountFoundByIdException();
        }
    }

    public String getBranchId() {
        return branchId;
    }
}
