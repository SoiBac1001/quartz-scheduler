package com.example.demospringquartz.config;

import com.example.demospringquartz.listener.HelloWorldJobExecutionListener;
import com.example.demospringquartz.listener.HelloWorldStepExecutionListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@AllArgsConstructor
@Slf4j
public class BatchConfiguration {
    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory steps;

    private final HelloWorldJobExecutionListener helloWorldJobExecutionListener;

    private final HelloWorldStepExecutionListener helloWorldStepExecutionListener;

    @Bean
    public Step step1() {
        return steps.get("step1")
                .listener(helloWorldStepExecutionListener)
                .tasklet(helloWorldTasklet())
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

    @Bean
    Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .listener(helloWorldJobExecutionListener)
                .start(step1())
                .build();
    }
}
