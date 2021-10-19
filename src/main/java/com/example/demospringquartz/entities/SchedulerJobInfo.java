package com.example.demospringquartz.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "SCHEDULER_JOB_INFO")
public class SchedulerJobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String jobId;

    private String jobName;
    private String jobGroup;
    private String jobStatus;
    private String jobClass;
    private String cronExpression;
    private String interfaceName;
    private Long repeatTime;
    private Boolean cronJob;
}
