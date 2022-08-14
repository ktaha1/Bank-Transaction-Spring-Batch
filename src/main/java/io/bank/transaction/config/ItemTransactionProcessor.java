package io.bank.transaction.config;

import io.bank.transaction.model.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ItemTransactionProcessor implements ItemProcessor<Transaction, Transaction> {
    @Override
    public Transaction process(Transaction transaction) {
        String strDate = transaction.getStrDate();
        transaction.setDate(LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        return transaction;
    }
}
