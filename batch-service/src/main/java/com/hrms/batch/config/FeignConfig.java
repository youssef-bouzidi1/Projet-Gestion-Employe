// com.hrms.batch.config.FeignConfig
package com.hrms.batch.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.hrms.batch.service")
public class FeignConfig {
}