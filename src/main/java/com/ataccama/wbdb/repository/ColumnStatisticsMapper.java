package com.ataccama.wbdb.repository;

import com.ataccama.wbdb.entity.ColumnStatistics;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnStatisticsMapper implements RowMapper<ColumnStatistics> {

    public ColumnStatistics mapRow(ResultSet rs, int rowNum) throws SQLException {
        ColumnStatistics columnStatistics = new ColumnStatistics();
        columnStatistics.setMin(rs.getString("column_min"));
        columnStatistics.setMax(rs.getString("column_max"));
        columnStatistics.setAvg(rs.getString("column_avg"));
        columnStatistics.setMedian(rs.getString("column_median"));
        return columnStatistics;
    }
}
