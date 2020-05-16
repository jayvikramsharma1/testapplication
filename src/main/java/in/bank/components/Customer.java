package in.bank.components;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Customer {
    private List<BankAccount> accounts = new LinkedList<>();
    private String panNumber;

    public Customer(final String panNumber) {
        this.panNumber = panNumber;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public String getPanNumber() {
        return panNumber;
    }
}
