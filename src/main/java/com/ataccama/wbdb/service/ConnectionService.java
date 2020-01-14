package com.ataccama.wbdb.service;

import com.ataccama.wbdb.dto.request.ConnectionRequest;
import com.ataccama.wbdb.dto.response.ConnectionResponse;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface ConnectionService {

    /**
     * Listing of all connections.
     *
     * @return list of all connections
     */
    List<ConnectionResponse> listConnections();

    /**
     * Listing of connection by id.
     *
     * @return connection
     * @exception EmptyResultDataAccessException if connection by id is not found
     */
    ConnectionResponse getConnection(Long connectionId);

    /**
     * Creation of new connection.
     *
     * @param connectionRequest new connection data without id parameter
     * @return newly created connection
     */
    ConnectionResponse createConnection(ConnectionRequest connectionRequest);

    /**
     * Update of existing connection.
     *
     * @param connectionId connection to be updated
     * @param connectionRequest new connection data without id parameter
     * @return updated connection
     * @exception EmptyResultDataAccessException if connection by id is not found
     */
    ConnectionResponse updateConnection(Long connectionId, ConnectionRequest connectionRequest);

    /**
     * Deletion of connection by id.
     *
     * @param connectionId connection to be deleted
     * @exception EmptyResultDataAccessException if connection by id is not found
     */
    void deleteConnection(Long connectionId);
}
