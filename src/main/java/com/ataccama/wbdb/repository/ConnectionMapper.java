package com.ataccama.wbdb.repository;


import com.ataccama.wbdb.entity.Connection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMapper implements RowMapper<Connection> {

    public Connection mapRow(ResultSet rs, int rowNum) throws SQLException {
        Connection connection = new Connection();
        connection.setId(rs.getLong("id"));
        connection.setName(rs.getString("name"));
        connection.setHostname(rs.getString("hostname"));
        connection.setPort(rs.getLong("port"));
        connection.setDatabaseName(rs.getString("database_name"));
        connection.setUsername(rs.getString("username"));
        connection.setPassword(rs.getString("password"));
        return connection;
    }
}
