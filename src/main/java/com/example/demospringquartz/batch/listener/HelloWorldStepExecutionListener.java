package com.example.demospringquartz.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloWorldStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        log.info("This is from before step execution {}", jobExecution.getExecutionContext());
        log.info("Inside step - print job parameter {} - jobId {}", jobExecution.getJobParameters(), jobExecution.getJobId());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("This is from after step execution {}", stepExecution.getJobExecution().getExecutionContext());
        return null;
    }
}
