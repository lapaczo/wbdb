package com.ataccama.wbdb.repository.impl;

import com.ataccama.wbdb.entity.Connection;
import com.ataccama.wbdb.repository.ConnectionDao;
import com.ataccama.wbdb.repository.ConnectionMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ConnectionDaoImpl extends NamedParameterJdbcDaoSupport implements ConnectionDao {

    public ConnectionDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public Connection create(Connection entity) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(entity);

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.getJdbcTemplate());
        simpleJdbcInsert
                .withTableName("connection")
                .usingGeneratedKeyColumns("id");

        Long id = (Long) simpleJdbcInsert.executeAndReturnKey(params);
        entity.setId(id);
        return entity;
    }

    @Override
    public Connection update(Connection entity) {
        String sql = "update connection set id = :id , name = :name , hostname = :hostname , " +
                "port = :port , database_name = :databaseName , username = :username , password = :password " +
                "where id = :id";

        SqlParameterSource params = new BeanPropertySqlParameterSource(entity);
        int rows = this.getNamedParameterJdbcTemplate().update(sql, params);
        if (rows != 1) {
            throw new IllegalStateException("Wrong number of rows were update for connection with id: " + entity.getId());
        }
        return entity;
    }

    @Override
    public Connection findById(Long id) {
        String sql = "SELECT * FROM connection where id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        return this.getNamedParameterJdbcTemplate().queryForObject(sql, params, new ConnectionMapper());
    }

    @Override
    public List<Connection> findAll() {
        String sql = "SELECT * FROM connection";
        return this.getJdbcTemplate().query(sql, new ConnectionMapper());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from connection where id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        this.getNamedParameterJdbcTemplate().update(sql, params);
    }
}
