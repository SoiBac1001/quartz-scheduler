package com.example.demospringquartz.service.impl;

import com.example.demospringquartz.component.JobScheduleCreator;
import com.example.demospringquartz.entities.SchedulerJobInfo;
import com.example.demospringquartz.repository.SchedulerJobInfoRepository;
import com.example.demospringquartz.service.SchedulerJobService;
import lombok.AllArgsConstructor;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@AllArgsConstructor
@Service
public class SchedulerJobServiceImpl implements SchedulerJobService {
    private final SchedulerFactoryBean schedulerFactoryBean;
    private final SchedulerJobInfoRepository schedulerRepository;
    private final ApplicationContext applicationContext;
    private final JobScheduleCreator jobScheduleCreator;

    @Override
    public void startAllSchedulers() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<SchedulerJobInfo> jobInfoList = schedulerRepository.findAllOrderByJobName();
        if(!CollectionUtils.isEmpty(jobInfoList)){

        }
    }

    private void scheduleJob(Scheduler scheduler, SchedulerJobInfo jobInfo) {
        String jobName = jobInfo.getJobName();
        String jobGroup = jobInfo.getJobGroup();
        String jobInfoCronExpression = jobInfo.getCronExpression();

        // flow here
    }
}
