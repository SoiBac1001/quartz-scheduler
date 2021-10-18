package com.example.demospringquartz.service.impl;

import com.example.demospringquartz.jobs.EmailJob;
import com.example.demospringquartz.payload.EmailRequest;
import com.example.demospringquartz.payload.EmailResponse;
import com.example.demospringquartz.service.EmailSchedulerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class EmailSchedulerServiceImpl implements EmailSchedulerService {
    private final Scheduler scheduler;

    @Override
    public EmailResponse scheduleEmail(EmailRequest emailRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(emailRequest.getDateTime(), emailRequest.getZoneId());

            if(dateTime.isBefore(ZonedDateTime.now())){
                // throw exception
                return EmailResponse.builder()
                        .success(false)
                        .message("dateTime must be after current time.")
                        .build();
            }

            JobDetail jobDetail = buildJobDetail(emailRequest);
            Trigger trigger = buildTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            JobKey jobKey = jobDetail.getKey();
            return EmailResponse.builder()
                    .success(true)
                    .jobId(jobKey.getName())
                    .jobGroup(jobKey.getGroup())
                    .message("Email scheduled successfully !")
                    .build();
        } catch (SchedulerException e) {
            log.error("Error while scheduling email: ", e);
            return EmailResponse.builder()
                    .success(false)
                    .message("Error while scheduling email. Please try again later !")
                    .build();
        }
    }

    private JobDetail buildJobDetail(EmailRequest emailRequest){
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", emailRequest.getEmail());
        jobDataMap.put("subject", emailRequest.getSubject());
        jobDataMap.put("body", emailRequest.getBody());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
//                .requestRecovery()
                .build();
    }

    private Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("attach", "file.pdf");

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionFireNow())
                .usingJobData(jobDataMap)
                .build();
    }
}
