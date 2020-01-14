package com.ataccama.wbdb.repository;

import com.ataccama.wbdb.entity.Column;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnMapper implements RowMapper<Column> {

    public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
        Column column = new Column();
        column.setTableCatalog(rs.getString("table_catalog"));
        column.setTableSchema(rs.getString("table_schema"));
        column.setTableName(rs.getString("table_name"));
        column.setColumnName(rs.getString("column_name"));
        column.setOrdinalPosition(rs.getString("ordinal_position"));
        column.setColumnDefault(rs.getString("column_default"));
        column.setIsNullable(rs.getString("is_nullable"));
        column.setDataType(rs.getString("data_type"));
        column.setCharacterMaximumLength(rs.getString("character_maximum_length"));
        column.setCharacterOctetLength(rs.getString("character_octet_length"));
        column.setNumericPrecision(rs.getString("numeric_precision"));
        column.setTableCatalog(rs.getString("numeric_scale"));
        column.setTableSchema(rs.getString("datetime_precision"));
        column.setTableName(rs.getString("character_set_name"));
        column.setColumnName(rs.getString("collation_name"));
        column.setOrdinalPosition(rs.getString("column_type"));
        column.setColumnDefault(rs.getString("column_key"));
        column.setIsNullable(rs.getString("extra"));
        column.setDataType(rs.getString("privileges"));
        column.setCharacterMaximumLength(rs.getString("column_comment"));
        column.setCharacterOctetLength(rs.getString("is_generated"));
        column.setNumericPrecision(rs.getString("generation_expression"));
        return column;
    }
}
