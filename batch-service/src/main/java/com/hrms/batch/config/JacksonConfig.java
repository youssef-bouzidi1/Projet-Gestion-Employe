// com.hrms.batch.config.JacksonConfig
package com.hrms.batch.config;

import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Add this line
        objectMapper.getFactory().setStreamReadConstraints(StreamReadConstraints.builder().maxNestingDepth(2000).build());
        return objectMapper;
    }
}