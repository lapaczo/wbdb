package com.ataccama.wbdb.entity;

import lombok.Data;

@Data
public class Connection {

    private Long id;
    private String name;
    private String hostname;
    private Long port;
    private String databaseName;
    private String username;
    private String password;
}
