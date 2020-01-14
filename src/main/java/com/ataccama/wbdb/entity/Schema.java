package com.ataccama.wbdb.entity;

import lombok.Data;

@Data
public class Schema {

    private String catalogName;
    private String schemaName;
    private String defaultCharacterSetName;
    private String defaultCollationName;
    private String sqlPath;
}
