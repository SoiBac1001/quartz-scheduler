package com.example.demospringquartz.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Slf4j
@Component
public class EmailJob extends QuartzJobBean {
    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // It is a merge of the JobDataMap found on the JobDetail and the one found on the Trigger,
        // with the values in the latter overriding any same-named values in the former.
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        JobDataMap jobDataMap1 = jobExecutionContext.getJobDetail().getJobDataMap();

        String recipientEmail = jobDataMap.getString("email");
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");

        sendEmail(mailProperties.getUsername(), recipientEmail, subject, body);
    }

    private void sendEmail(String fromEmail, String toEmail, String subject, String body){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(toEmail);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error send email", e);
        }
    }
}
