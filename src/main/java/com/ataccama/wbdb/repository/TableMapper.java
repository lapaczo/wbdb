package com.ataccama.wbdb.repository;

import com.ataccama.wbdb.entity.Table;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableMapper implements RowMapper<Table> {

    public Table mapRow(ResultSet rs, int rowNum) throws SQLException {
        Table table = new Table();
        table.setTableCatalog(rs.getString("table_catalog"));
        table.setTableSchema(rs.getString("table_schema"));
        table.setTableName(rs.getString("table_name"));
        table.setTableType(rs.getString("table_type"));
        table.setEngine(rs.getString("engine"));
        table.setVersion(rs.getString("version"));
        table.setRowFormat(rs.getString("row_format"));
        table.setTableRows(rs.getString("table_rows"));
        table.setAvgRowLength(rs.getString("avg_row_length"));
        table.setDataLength(rs.getString("data_length"));
        table.setMaxDataLength(rs.getString("max_data_length"));
        table.setIndexLength(rs.getString("index_length"));
        table.setDataFree(rs.getString("data_free"));
        table.setAutoIncrement(rs.getString("auto_increment"));
        table.setCreateTime(rs.getString("create_time"));
        table.setUpdateTime(rs.getString("update_time"));
        table.setCheckTime(rs.getString("check_time"));
        table.setTableCollation(rs.getString("table_collation"));
        table.setChecksum(rs.getString("checksum"));
        table.setCreateOptions(rs.getString("create_options"));
        table.setTableComment(rs.getString("table_comment"));
        table.setMaxIndexLength(rs.getString("max_index_length"));
        table.setTemporary(rs.getString("temporary"));
        return table;
    }
}
