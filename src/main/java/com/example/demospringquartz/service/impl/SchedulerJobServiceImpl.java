package com.example.demospringquartz.service.impl;

import com.example.demospringquartz.component.JobScheduleCreator;
import com.example.demospringquartz.service.SchedulerJobService;
import lombok.AllArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.impl.SchedulerRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SchedulerJobServiceImpl implements SchedulerJobService {
    private final Scheduler scheduler;
    private final SchedulerFactoryBean schedulerFactoryBean;
    private final SchedulerRepository schedulerRepository;
    private final ApplicationContext applicationContext;
    private final JobScheduleCreator jobScheduleCreator;
}
