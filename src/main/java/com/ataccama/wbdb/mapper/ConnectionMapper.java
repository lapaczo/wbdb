package com.ataccama.wbdb.mapper;

import com.ataccama.wbdb.dto.request.ConnectionRequest;
import com.ataccama.wbdb.dto.response.ConnectionResponse;
import com.ataccama.wbdb.entity.Connection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ConnectionMapper {

    public abstract ConnectionResponse entityToResponse(Connection entity);

    public abstract Connection requestToEntity(ConnectionRequest request);

    public void copyRequestToEntity(Connection entity, ConnectionRequest request) {
        entity.setName(request.getName());
        entity.setHostname(request.getHostname());
        entity.setPort(request.getPort());
        entity.setDatabaseName(request.getDatabaseName());
        entity.setUsername(request.getUsername());
        entity.setPassword(request.getPassword());
    }
}
