package com.example.demospringquartz.repository;

import com.example.demospringquartz.entities.SchedulerJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchedulerJobInfoRepository extends JpaRepository<SchedulerJobInfo, Long> {
    Optional<SchedulerJobInfo> findByJobName(String jobName);
}
