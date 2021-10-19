package com.example.demospringquartz.component;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.SimpleTrigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
@Slf4j
public class JobScheduleCreator {
    public CronTrigger createCronTrigger(String triggerName, Date startTime, String cronExpression, int misFireInstruction){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(misFireInstruction); // clarify
        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return factoryBean.getObject();
    }

    public SimpleTrigger createSimpleTrigger(String triggerName, Date startTime, Long repeatTime, int misFireInstruction){
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setRepeatInterval(repeatTime);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(misFireInstruction);
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }
}
