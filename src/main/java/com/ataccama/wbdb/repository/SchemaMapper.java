package com.ataccama.wbdb.repository;

import com.ataccama.wbdb.entity.Schema;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SchemaMapper implements RowMapper<Schema> {

    public Schema mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schema schema = new Schema();
        schema.setCatalogName(rs.getString("catalog_name"));
        schema.setSchemaName(rs.getString("schema_name"));
        schema.setDefaultCharacterSetName(rs.getString("default_character_set_name"));
        schema.setDefaultCollationName(rs.getString("default_collation_name"));
        schema.setSqlPath(rs.getString("sql_path"));
        return schema;
    }
}
