package com.ataccama.wbdb.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ConnectionRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String hostname;
    @NotNull
    private Long port;
    @NotEmpty
    private String databaseName;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    @Override
    public String toString() {
        return "ConnectionRequest{" +
                "name='" + name + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", databaseName='" + databaseName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
