package com.example.demospringquartz.config;

import com.example.demospringquartz.batch.listener.HelloWorldJobExecutionListener;
import com.example.demospringquartz.batch.listener.HelloWorldStepExecutionListener;
import com.example.demospringquartz.batch.processor.InMemItemProcessor;
import com.example.demospringquartz.batch.reader.InMemReader;
import com.example.demospringquartz.batch.writer.ConsoleItemWriter;
import com.example.demospringquartz.entities.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@EnableBatchProcessing
@Configuration
@AllArgsConstructor
@Slf4j
public class BatchConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory steps;
    private final HelloWorldJobExecutionListener helloWorldJobExecutionListener;
    private final HelloWorldStepExecutionListener helloWorldStepExecutionListener;
    private final InMemItemProcessor inMemItemProcessor;

    // Repository
    private final JobRepository jobRepository;

    @Bean
    public InMemReader reader() {
        return new InMemReader();
    }

    @Bean
    public Step step1() {
        return steps.get("step1")
                .listener(helloWorldStepExecutionListener)
                .tasklet(helloWorldTasklet())
                .build();
    }

    @Bean
    public Step step2() {
        return steps.get("step2").
                <Integer, Integer>
                chunk(3)
                .reader(reader())
                .processor(inMemItemProcessor)
                .writer(new ConsoleItemWriter())
                .build();
    }

    private Tasklet helloWorldTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                log.info("Hello World");
                return RepeatStatus.FINISHED;
            }
        };

        /*return (stepContribution, chunkContext) -> {
            System.out.println("Hello World");
            return RepeatStatus.FINISHED;
        };*/
    }

    public FlatFileItemReader flatFileItemReader() {
        FlatFileItemReader reader = new FlatFileItemReader();
        // step 1: let reader know where is the file ?
        reader.setResource(new FileSystemResource("input/product.csv"));

        // create the line mapper
        reader.setLineMapper(
                new DefaultLineMapper<Product>(){
                    {
                        setLineTokenizer(new DelimitedLineTokenizer() {
                            {
                                setNames(new String[]{"productID","productName","productDesc","price","unit"});
                            }
                        });

                        setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {
                            {
                                setTargetType(Product.class);
                            }
                        });
                    }
                }
        );

        // step 3: tell reader to skip the header
        reader.setLinesToSkip(1);

        return reader;
    }

    @Bean
    public Job helloWorldJob() {
        boolean jobInstanceExists = jobRepository.isJobInstanceExists("helloWorldJob", new JobParameters());

        return jobBuilderFactory.get("helloWorldJob")
                .listener(helloWorldJobExecutionListener)
                .start(step1())
                .next(step2())
                .build();
    }
}
