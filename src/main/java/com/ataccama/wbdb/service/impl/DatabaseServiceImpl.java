package com.ataccama.wbdb.service.impl;

import com.ataccama.wbdb.entity.Column;
import com.ataccama.wbdb.entity.ColumnStatistics;
import com.ataccama.wbdb.entity.Schema;
import com.ataccama.wbdb.entity.Table;
import com.ataccama.wbdb.entity.TableStatistics;
import com.ataccama.wbdb.repository.DatabaseDao;
import com.ataccama.wbdb.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DatabaseServiceImpl implements DatabaseService {

    private final DatabaseDao repository;

    public DatabaseServiceImpl(DatabaseDao repository) {
        this.repository = repository;
    }

    @Override
    public List<Schema> listSchemas() {
        log.info("List all schemas.");
        return repository.findAllSchema();
    }

    @Override
    public List<Table> listTables() {
        log.info("List all tables.");
        return repository.findAllTable();
    }

    @Override
    public List<Column> listColumns() {
        log.info("List all columns.");
        return repository.findAllColumns();
    }

    @Override
    public List<Map<String, Object>> listRows(String schemaName, String tableName) {
        log.info("List all data rows for table {}.{}.", schemaName, tableName);
        return repository.findAllRows(schemaName, tableName);
    }

    @Override
    public ColumnStatistics getColumnStatistics(String schemaName, String tableName, String columnName) {
        log.info("List column statistics for column {} in {}.{}.", columnName, schemaName, tableName);
        return repository.findColumnStatistics(schemaName, tableName, columnName);
    }

    @Override
    public TableStatistics getTableStatistics(String schemaName, String tableName) {
        log.info("List table statistics for table {}.{}.", schemaName, tableName);
        return repository.findTableStatistics(schemaName, tableName);
    }
}
