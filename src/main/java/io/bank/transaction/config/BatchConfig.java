package io.bank.transaction.config;

import io.bank.transaction.model.Transaction;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired private JobBuilderFactory job;
    @Autowired private StepBuilderFactory step;
    @Autowired private ItemReader<Transaction> itemReader;
    @Autowired private ItemProcessor<Transaction, Transaction> itemProcessor;
    @Autowired private ItemWriter<Transaction> itemWriter;
    


}
