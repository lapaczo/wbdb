package com.ataccama.wbdb.controller;

import com.ataccama.wbdb.dto.request.ConnectionRequest;
import com.ataccama.wbdb.dto.response.ConnectionResponse;
import com.ataccama.wbdb.entity.Column;
import com.ataccama.wbdb.entity.ColumnStatistics;
import com.ataccama.wbdb.entity.Schema;
import com.ataccama.wbdb.entity.Table;
import com.ataccama.wbdb.entity.TableStatistics;
import com.ataccama.wbdb.service.ConnectionService;
import com.ataccama.wbdb.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/connections")
public class ConnectionController {

    private final ConnectionService connectionService;
    private final DatabaseService databaseService;
    private final Map<Object, Object> dataSourceMap;

    public ConnectionController(ConnectionService connectionService,
                                DatabaseService databaseService,
                                Map<Object, Object> dataSourceMap) {
        this.connectionService = connectionService;
        this.databaseService = databaseService;
        this.dataSourceMap = dataSourceMap;
    }

    @GetMapping("/{id}")
    public ConnectionResponse get(@PathVariable Long id) {
        return connectionService.getConnection(id);
    }

    @GetMapping
    public List<ConnectionResponse> list() {
        return connectionService.listConnections();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ConnectionResponse create(@RequestBody @Valid ConnectionRequest request) {
        return connectionService.createConnection(request);
    }

    @PutMapping("/{id}")
    public ConnectionResponse update(@PathVariable Long id, @RequestBody @Valid ConnectionRequest request) {
        return connectionService.updateConnection(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        connectionService.deleteConnection(id);
    }

    @GetMapping("/{connectionId}/schemas")
    public List<Schema> listSchema(@PathVariable Long connectionId) {
        connectionExists(connectionId);
        return databaseService.listSchemas();
    }

    @GetMapping("/{connectionId}/tables")
    public List<Table> listTables(@PathVariable Long connectionId) {
        connectionExists(connectionId);
        return databaseService.listTables();
    }

    @GetMapping("/{connectionId}/columns")
    public List<Column> listColumns(@PathVariable Long connectionId) {
        connectionExists(connectionId);
        return databaseService.listColumns();
    }

    @GetMapping("/{connectionId}/schemas/{schemaName}/tables/{tableName}/rows")
    public List<Map<String, Object>> listRows(@PathVariable Long connectionId,
                                                 @PathVariable String schemaName,
                                                 @PathVariable String tableName) {
        connectionExists(connectionId);
        return databaseService.listRows(schemaName, tableName);
    }

    @GetMapping("/{connectionId}/schemas/{schemaName}/tables/{tableName}/columns/{columnName}/statistics")
    public ColumnStatistics listColumnStatistics(@PathVariable Long connectionId,
                                        @PathVariable String schemaName,
                                        @PathVariable String tableName,
                                        @PathVariable String columnName) {
        connectionExists(connectionId);
        return databaseService.getColumnStatistics(schemaName, tableName, columnName);
    }

    @GetMapping("/{connectionId}/schemas/{schemaName}/tables/{tableName}/statistics")
    public TableStatistics listTablesStatistics(@PathVariable Long connectionId,
                                                @PathVariable String schemaName,
                                                @PathVariable String tableName) {
        connectionExists(connectionId);
        return databaseService.getTableStatistics(schemaName, tableName);
    }

    private void connectionExists(Long connectionId) {
        if (!dataSourceMap.containsKey(connectionId)) {
            log.error("Connection with id: {} not found.", connectionId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Connection not found with id: " + connectionId);
        }
    }
}
