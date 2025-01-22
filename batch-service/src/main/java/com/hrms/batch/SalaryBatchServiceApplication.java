// com.hrms.batch.SalaryBatchServiceApplication.java
package com.hrms.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
public class SalaryBatchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaryBatchServiceApplication.class, args);
    }

}