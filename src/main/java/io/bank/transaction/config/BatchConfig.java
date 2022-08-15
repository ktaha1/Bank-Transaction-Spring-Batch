package io.bank.transaction.config;

import io.bank.transaction.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig extends DefaultBatchConfigurer {
    @Autowired private JobBuilderFactory job;
    @Autowired private StepBuilderFactory step;

    @Value("${inputFilePath}") Resource resource;
    /*@Autowired private ItemReader<Transaction> itemReader;
    @Autowired private ItemProcessor<Transaction, Transaction> itemProcessor;
    @Autowired private ItemWriter<Transaction> itemWriter;*/

    @Override
    public void setDataSource(DataSource dataSource) {
        // override to do not set datasource even if a datasource exist.
        // initialize will use a Map based JobRepository (instead of database)
    }

    @Bean
    public FlatFileItemReader<Transaction> reader(){
        log.info("Writing transactions ....");
        return new FlatFileItemReaderBuilder<Transaction>()
                .name("Transaction-Reader")
                .resource(resource)
                .delimited()
                .names("id", "fullName", "date", "type", "amount")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Transaction>() {{
                    setTargetType(Transaction.class);
                }})
                .build();
    }

    @Bean
    public ItemTransactionProcessor process(){
        return new ItemTransactionProcessor();
    }

    @Bean
    public ItemTransactionWriter writer(){
        return new ItemTransactionWriter();
    }

    @Bean
    public Step stepOne(){
        log.info("Step Executing ...");
        return this.step.get("stepOne")
                .<Transaction, Transaction>chunk(10)
                .reader(reader())
                .processor(process())
                .writer(writer())
                .build();
    }

    @Bean
    public Job executeJob(){
        log.info("Job Executing ...");
        return job.get("mainJob")
                .start(stepOne())
                .build();
    }


}
