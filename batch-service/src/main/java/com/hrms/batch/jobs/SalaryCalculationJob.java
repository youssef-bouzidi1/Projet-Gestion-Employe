// com.hrms.batch.jobs.SalaryCalculationJob
package com.hrms.batch.jobs;

import com.hrms.batch.model.Employee;
import com.hrms.batch.model.Salary;
import com.hrms.batch.repository.SalaryRepository;
import com.hrms.batch.service.EmployeeServiceClient;
import com.hrms.batch.jobs.SalaryItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;


@Configuration
public class SalaryCalculationJob {

    @Bean
    public ItemReader<Employee> employeeReader(EmployeeServiceClient employeeServiceClient) {
        return new ItemReader<Employee>() {
            private List<Employee> employees = null;
            private int index = 0;
            @Override
            public Employee read() {
                if (employees == null){
                    employees = employeeServiceClient.getAllEmployees();
                }
                if (index >= employees.size()) return null;
                return employees.get(index++);
            }
        };
    }

    @Bean
    public SalaryItemProcessor salaryProcessor() {
        return new SalaryItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<Salary> salaryWriter(SalaryRepository salaryRepository) {
        RepositoryItemWriter<Salary> writer = new RepositoryItemWriter<>();
        writer.setRepository(salaryRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Job calculateMonthlySalaries(
            JobRepository jobRepository,
            Step calculateSalariesStep) {
        return new JobBuilder("calculateMonthlySalaries", jobRepository)
                .start(calculateSalariesStep)
                .build();
    }

    @Bean
    public Step calculateSalariesStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<Employee> reader,
            SalaryItemProcessor processor,
            RepositoryItemWriter<Salary> writer) {
        return new StepBuilder("calculateSalariesStep", jobRepository)
                .<Employee, Salary>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}