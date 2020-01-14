package com.ataccama.wbdb.repository;

import com.ataccama.wbdb.entity.Column;
import com.ataccama.wbdb.entity.ColumnStatistics;
import com.ataccama.wbdb.entity.Schema;
import com.ataccama.wbdb.entity.Table;
import com.ataccama.wbdb.entity.TableStatistics;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface DatabaseDao {

    /**
     * Finding of all schemas.
     *
     * @return list of all schemas
     */
    @Transactional(readOnly = true)
    List<Schema> findAllSchema();

    /**
     * Finding of all tables.
     *
     * @return list of all tables
     */
    @Transactional(readOnly = true)
    List<Table> findAllTable();

    /**
     * Finding of all columns.
     *
     * @return list of all columns
     */
    @Transactional(readOnly = true)
    List<Column> findAllColumns();

    /**
     * Finding of all data rows for a table in a schema.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @return list of all data rows
     */
    @Transactional(readOnly = true)
    List<Map<String, Object>> findAllRows(String schemaName, String tableName);

    /**
     * Listing of column statistics for a table in a schema.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @param columnName column name
     * @return list of column statistics
     */
    @Transactional(readOnly = true)
    ColumnStatistics findColumnStatistics(String schemaName, String tableName, String columnName);

    /**
     * Listing of table statistics in a schema.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @return list of table statistics
     */
    @Transactional(readOnly = true)
    TableStatistics findTableStatistics(String schemaName, String tableName);
}
