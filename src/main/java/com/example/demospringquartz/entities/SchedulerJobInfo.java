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

    private String jobPackage;
    private String jobClass;

    private String jobStatus;
    private String cronExpression;
    private int priority;
    private String remark;
    private String disabled;
    private String interfaceName;
    private Long repeatTime;
    private Boolean cronJob;
}
