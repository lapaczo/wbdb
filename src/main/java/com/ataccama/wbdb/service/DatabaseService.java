package com.ataccama.wbdb.service;

import com.ataccama.wbdb.entity.Column;
import com.ataccama.wbdb.entity.ColumnStatistics;
import com.ataccama.wbdb.entity.Schema;
import com.ataccama.wbdb.entity.Table;
import com.ataccama.wbdb.entity.TableStatistics;

import java.util.List;
import java.util.Map;

public interface DatabaseService {

    /**
     * Listing of all schemas.
     *
     * @return list of all schemas
     */
    List<Schema> listSchemas();

    /**
     * Listing of all tables.
     *
     * @return list of all tables
     */
    List<Table> listTables();

    /**
     * Listing of all columns.
     *
     * @return list of all columns
     */
    List<Column> listColumns();

    /**
     * Listing of all data rows for a table in a schema.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @return list of all data rows
     */
    List<Map<String, Object>> listRows(String schemaName, String tableName);

    /**
     * Listing of column statistics for a table in a schema.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @param columnName column name
     * @return list of column statistics
     */
    ColumnStatistics getColumnStatistics(String schemaName, String tableName, String columnName);

    /**
     * Listing of table statistics in a schema.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @return list of table statistics
     */
    TableStatistics getTableStatistics(String schemaName, String tableName);
}
