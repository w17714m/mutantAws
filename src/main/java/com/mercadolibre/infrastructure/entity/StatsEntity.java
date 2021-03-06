package com.mercadolibre.infrastructure.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "java-stats-dev-v1")
public class StatsEntity {
    private String id;
    private String tipo;
    private Float total;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName = "tipo")
    public String getTipo() {
        return this.tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @DynamoDBAttribute(attributeName = "total")
    public Float getTotal() {
        return this.total;
    }
    public void setTotal(Float total) {
        this.total = total;
    }


    public String toString() {
        return String.format("Stats [id=%s, name=%s, total=$%f]", this.id, this.tipo, this.total);
    }
}
