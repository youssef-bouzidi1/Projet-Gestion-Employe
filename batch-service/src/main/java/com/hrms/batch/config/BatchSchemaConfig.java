package com.hrms.batch.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BatchSchemaConfig {

    @Bean
    public SqlDataSourceScriptDatabaseInitializer batchDataSourceInitializer(
            @Qualifier("dataSource") DataSource dataSource,
            SqlInitializationProperties properties
    ) {
        SqlInitializationProperties sqlProperties = new SqlInitializationProperties();
        sqlProperties.setMode(properties.getMode());
        sqlProperties.setSchemaLocations(properties.getSchemaLocations());
        return new SqlDataSourceScriptDatabaseInitializer(dataSource, sqlProperties);
    }
}