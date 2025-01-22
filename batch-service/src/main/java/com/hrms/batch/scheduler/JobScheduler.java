// com.hrms.batch.scheduler.JobScheduler
package com.hrms.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JobScheduler {
    private final JobLauncher jobLauncher;
    private final Job calculateMonthlySalaries;

    @Scheduled(cron = "0 0 1 1 * ?") // Run at 1 AM on the 1st day of each month
    public void runSalaryCalculationJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(calculateMonthlySalaries, params);
    }
}