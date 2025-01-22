package com.hrms.batch.service;

import com.hrms.batch.model.Employee;
import com.hrms.batch.model.Salary;
import com.hrms.batch.repository.SalaryRepository;
import com.hrms.batch.jobs.SalaryItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class SalaryCalculationService {
    @Autowired
    private  JobLauncher jobLauncher;
    @Autowired
    private  Job calculateMonthlySalaries;
    private static final Logger logger = LoggerFactory.getLogger(SalaryCalculationService.class);

    public void generateSalaries() {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        logger.info("Starting Salary Calculation Job");

        try {
            JobExecution jobExecution = jobLauncher.run(calculateMonthlySalaries, params);
            if(jobExecution.getExitStatus().getExitCode().equals("COMPLETED")){
                logger.info("Salary Calculation job finished with success");
            } else {
                logger.error("Salary Calculation job finished with status {}", jobExecution.getExitStatus());
                throw new RuntimeException("Salary calculation job failed");
            }

        } catch (Exception e) {
            logger.error("Error on executing the job: ", e);
            throw new RuntimeException("Error on executing the job", e); // Rethrow with the original exception
        }
    }
}