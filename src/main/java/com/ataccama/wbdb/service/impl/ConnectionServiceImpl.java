package com.ataccama.wbdb.service.impl;

import com.ataccama.wbdb.dto.request.ConnectionRequest;
import com.ataccama.wbdb.dto.response.ConnectionResponse;
import com.ataccama.wbdb.entity.Connection;
import com.ataccama.wbdb.mapper.ConnectionMapper;
import com.ataccama.wbdb.repository.ConnectionDao;
import com.ataccama.wbdb.service.ConnectionService;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConnectionServiceImpl implements ConnectionService, InitializingBean {

    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String BASE_URL = "jdbc:mariadb://";

    private final Map<Object, Object> dataSourceMap;
    private final ConnectionDao repository;
    private final ConnectionMapper mapper;

    public ConnectionServiceImpl(Map<Object, Object> dataSourceMap,
                                 ConnectionDao repository,
                                 ConnectionMapper mapper) {
        this.dataSourceMap = dataSourceMap;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ConnectionResponse> listConnections() {
        log.info("Listing of all connections.");
        return repository.findAll().stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConnectionResponse getConnection(Long connectionId) {
        log.info("Get connection by id: {}.", connectionId);
        return mapper.entityToResponse(repository.findById(connectionId));
    }

    @Override
    @Transactional
    public ConnectionResponse createConnection(ConnectionRequest request) {
        log.info("Create new connection: {}", request);
        Connection connection = repository.create(mapper.requestToEntity(request));
        createDataSource(connection);
        return mapper.entityToResponse(connection);
    }

    @Override
    @Transactional
    public ConnectionResponse updateConnection(Long connectionId, ConnectionRequest connectionRequest) {
        log.info("Update connection with id: {}, {}", connectionId, connectionRequest);
        Connection connection = repository.findById(connectionId);
        mapper.copyRequestToEntity(connection, connectionRequest);

        log.info("Data source with id: {} were removed.", connectionId);
        removeDataSource(connectionId);

        Connection updated = repository.update(connection);

        createDataSource(updated);
        return mapper.entityToResponse(updated);
    }

    @Override
    @Transactional
    public void deleteConnection(Long connectionId) {
        log.info("Delete connection with id: {}", connectionId);
        repository.findById(connectionId);

        repository.deleteById(connectionId);

        log.info("Data source with id: {} were removed.", connectionId);
        removeDataSource(connectionId);
    }

    @Override
    public void afterPropertiesSet() {
        repository.findAll().forEach(connection -> {
            try {
                createDataSource(connection);
            } catch (Exception ex) {
                log.error("Failed to create data source for connection details: {}.", connection, ex);
            }
        });
    }

    private void createDataSource(Connection connection) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(connection.getUsername());
        ds.setPassword(connection.getPassword());
        String url = BASE_URL + connection.getHostname() + ":" + connection.getPort() + "/" + connection.getDatabaseName();
        ds.setJdbcUrl(url);
        ds.setDriverClassName(DRIVER);

        dataSourceMap.put(connection.getId(), ds);
        log.info("Data source with id: {} created for connection: {}", connection.getId(), connection);
    }

    private void removeDataSource(Long id) {
        HikariDataSource ds = (HikariDataSource) dataSourceMap.get(id);
        ds.close();
        dataSourceMap.remove(id);
    }
}
