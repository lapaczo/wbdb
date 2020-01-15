package com.ataccama.wbdb.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.Map;

@Configuration
@Slf4j
public class PreDestroyConfig {

    private final Map<Object, Object> dataSourceMap;

    public PreDestroyConfig(Map<Object, Object> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }

    @PreDestroy
    public void destroyDataSource() {
        dataSourceMap.forEach((key, value) -> {
            try {
                ((HikariDataSource) value).close();
            } catch (Exception ex) {
                log.error("Cannot close data source with id: {}", key);
            }
        });
    }
}
