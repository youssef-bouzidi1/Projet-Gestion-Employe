// com.hrms.salary.SalaryServiceApplication.java
package com.hrms.salary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hrms.salary.service")
public class SalaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaryServiceApplication.class, args);
    }
}