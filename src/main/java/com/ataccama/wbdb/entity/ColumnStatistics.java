package com.ataccama.wbdb.entity;

import lombok.Data;

@Data
public class ColumnStatistics {

    private String min;
    private String max;
    private String avg;
    private String median;
}
