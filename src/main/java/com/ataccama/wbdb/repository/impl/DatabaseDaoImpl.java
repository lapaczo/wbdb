package com.ataccama.wbdb.repository.impl;

import com.ataccama.wbdb.entity.Column;
import com.ataccama.wbdb.entity.ColumnStatistics;
import com.ataccama.wbdb.entity.Schema;
import com.ataccama.wbdb.entity.Table;
import com.ataccama.wbdb.entity.TableStatistics;
import com.ataccama.wbdb.repository.ColumnMapper;
import com.ataccama.wbdb.repository.ColumnStatisticsMapper;
import com.ataccama.wbdb.repository.DatabaseDao;
import com.ataccama.wbdb.repository.SchemaMapper;
import com.ataccama.wbdb.repository.TableMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseDaoImpl extends NamedParameterJdbcDaoSupport implements DatabaseDao {

    public DatabaseDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Schema> findAllSchema() {
        String sql = "select * from information_schema.schemata";
        return this.getJdbcTemplate().query(sql, new SchemaMapper());
    }

    @Override
    public List<Table> findAllTable() {
        String sql = "select * from information_schema.tables";
        return this.getJdbcTemplate().query(sql, new TableMapper());
    }

    @Override
    public List<Column> findAllColumns() {
        String sql = "select * from information_schema.columns";
        return this.getJdbcTemplate().query(sql, new ColumnMapper());
    }

    @Override
    public List<Map<String, Object>> findAllRows(String schemaName, String tableName) {
        String sql = "select * from " + schemaName + "." + tableName;
        ColumnMapRowMapper rowMapper = new ColumnMapRowMapper();
        return this.getNamedParameterJdbcTemplate().query(sql, rowMapper);
    }

    @Override
    public ColumnStatistics findColumnStatistics(String schemaName, String tableName, String columnName) {
        String sql = "select min( " + columnName + " ) as column_min, " +
                "max( " + columnName + " ) as column_max, " +
                "avg( " + columnName + " ) as column_avg, " +
                "median( " + columnName + " ) OVER (PARTITION BY " + columnName + ") as column_median " +
                "from " + schemaName + "." + tableName;
        return this.getNamedParameterJdbcTemplate()
                .queryForObject(sql, new MapSqlParameterSource(), new ColumnStatisticsMapper());
    }

    @Override
    public TableStatistics findTableStatistics(String schemaName, String tableName) {
        String recordsSql = "select count( * ) as column_min from " + schemaName + "." + tableName;
        Long records = this.getNamedParameterJdbcTemplate()
                .queryForObject(recordsSql, new MapSqlParameterSource(), Long.class);

        String attributesSql = "select count( * ) from information_schema.columns " +
                "where table_schema = :schemaName and table_name = :tableName";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("schemaName", schemaName);
        params.addValue("tableName", tableName);
        Long attributes = this.getNamedParameterJdbcTemplate()
                .queryForObject(attributesSql, params, Long.class);

        return new TableStatistics(records, attributes);
    }
}
