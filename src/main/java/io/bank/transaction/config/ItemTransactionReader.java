package io.bank.transaction.config;

import io.bank.transaction.model.Transaction;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ItemTransactionReader implements ItemReader<Transaction> {
    @Value("${inputFilePath}") Resource resource;
    @Override
    public Transaction read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        FlatFileItemReader<Transaction> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("csv-reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader.read();
    }

    private LineMapper<Transaction> lineMapper() {
        return null;
    }
}
