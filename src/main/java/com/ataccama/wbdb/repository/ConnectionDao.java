package com.ataccama.wbdb.repository;

import com.ataccama.wbdb.entity.Connection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConnectionDao {

    /**
     * Find all connections.
     *
     * @return list of connections
     */
    @Transactional(readOnly = true)
    List<Connection> findAll();

    /**
     * Find connection by id.
     *
     * @return connection
     * @exception EmptyResultDataAccessException if connection by id is not found
     */
    @Transactional(readOnly = true)
    Connection findById(Long id);

    /**
     * Creation of new connection.
     *
     * @param connection new connection data without id parameter
     * @return newly created connection
     */
    @Transactional
    Connection create(Connection connection);

    /**
     * Update of existing connection.
     *
     * @param connection connection to be updated
     * @return updated connection
     */
    @Transactional
    Connection update(Connection connection);

    /**
     * Deletion of connection by id.
     *
     * @param id connection to be deleted
     */
    @Transactional
    void deleteById(Long id);
}
