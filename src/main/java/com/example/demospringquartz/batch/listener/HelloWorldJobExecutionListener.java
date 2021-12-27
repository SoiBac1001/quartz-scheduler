package com.example.demospringquartz.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloWorldJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Before starting the Job - Job Name {}", jobExecution.getJobInstance().getJobName());
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        log.info("Before starting the Job {}", executionContext);
        executionContext.put("My Name", "Michael");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After starting the Job {}", jobExecution.getExecutionContext());
    }
}
