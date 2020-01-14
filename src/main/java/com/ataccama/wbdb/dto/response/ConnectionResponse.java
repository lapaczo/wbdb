package com.ataccama.wbdb.dto.response;

import lombok.Data;

@Data
public class ConnectionResponse {

    private Long id;
    private String name;
    private String hostname;
    private Long port;
    private String databaseName;
    private String username;
}
