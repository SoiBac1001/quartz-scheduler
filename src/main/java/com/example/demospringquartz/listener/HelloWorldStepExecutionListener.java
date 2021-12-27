package com.example.demospringquartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloWorldStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("This is from before step execution {}", stepExecution.getJobExecution().getExecutionContext());
        log.info("Inside step - print job parameter {}", stepExecution.getJobExecution().getJobParameters());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("This is from after step execution {}", stepExecution.getJobExecution().getExecutionContext());
        return null;
    }
}
