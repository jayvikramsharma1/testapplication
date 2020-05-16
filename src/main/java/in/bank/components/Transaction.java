package in.bank.components;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Transaction {
    private String transactionId;
    private Double amount;
    private String type;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
