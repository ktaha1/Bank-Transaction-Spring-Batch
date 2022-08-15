package io.bank.transaction.config;

import io.bank.transaction.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class ItemTransactionWriter implements ItemWriter<Transaction> {
    @Override
    public void write(List<? extends Transaction> list) throws Exception {
        log.info("Writing transactions ....");
        list.forEach(System.out::println);
    }
}
