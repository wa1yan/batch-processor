package dev.waiyanhtet.batch_processor.config;

import dev.waiyanhtet.batch_processor.dto.TransactionHistoryDto;
import dev.waiyanhtet.batch_processor.entity.TransactionHistoryEntity;
import dev.waiyanhtet.batch_processor.mapper.TransactionHistoryMapper;
import dev.waiyanhtet.batch_processor.repository.TransactionHistoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final TransactionHistoryRepo transactionHistoryRepo;
    private final PlatformTransactionManager platformTransactionManager;
    private final TransactionHistoryMapper mapper;



    @Bean
    public Job runJob() {
        return new JobBuilder("importTxns", jobRepository)
                .start(importStep())
                .build();
    }

    @Bean
    public Step importStep() {
        return new StepBuilder("txtImport", jobRepository)
                .<TransactionHistoryDto, TransactionHistoryEntity>chunk(10, platformTransactionManager)
                //.allowStartIfComplete(true) //auto data save when application started
                .reader(itemReader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public FlatFileItemReader<TransactionHistoryDto> itemReader() {
        FlatFileItemReader<TransactionHistoryDto> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/dataSource.txt"));
        itemReader.setName("dataSourceReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public TransactionHistoryProcessor processor() {
        return new TransactionHistoryProcessor(mapper);
    }

    @Bean
    public RepositoryItemWriter<TransactionHistoryEntity> writer() {
        RepositoryItemWriter<TransactionHistoryEntity> writer = new RepositoryItemWriter<>();
        writer.setRepository(transactionHistoryRepo);
        writer.setMethodName("save");
        return writer;
    }


    @Bean
    public TaskExecutor taskExecutor() {
        var asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

    private LineMapper<TransactionHistoryDto> lineMapper() {
        DefaultLineMapper<TransactionHistoryDto> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("ACCOUNT_NUMBER", "TRX_AMOUNT", "DESCRIPTION", "TRX_DATE", "TRX_TIME", "CUSTOMER_ID");

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new TransactionHistoryFieldSetMapper());

        return lineMapper;
    }

}
