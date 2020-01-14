package com.ataccama.wbdb.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("master.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "master")
    public DataSource getMasterDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "dataSourceMap")
    public Map<Object, Object> getDataSourceMap() {
        return new HashMap<>();
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource(DataSource master, Map<Object, Object> dataSourceMap) {

        RoutingDataSource dataSource = new RoutingDataSource();
        dataSource.setDefaultTargetDataSource(master);
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }
}
