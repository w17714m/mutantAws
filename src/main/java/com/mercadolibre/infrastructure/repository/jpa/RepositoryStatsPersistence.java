package com.mercadolibre.infrastructure.repository.jpa;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.mercadolibre.domain.StatsDto;
import com.mercadolibre.domain.repository.RepositoryStats;
import com.mercadolibre.infrastructure.builder.StatsBuilder;
import com.mercadolibre.infrastructure.entity.StatsEntity;
import com.mercadolibre.infrastructure.repository.DynamoDBAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryStatsPersistence implements RepositoryStats {


    private static final String PRODUCTS_TABLE_NAME = System.getenv("PRODUCTS_TABLE_NAME");

    private static DynamoDBAdapter db_adapter;
    private AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    public RepositoryStatsPersistence() {
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(PRODUCTS_TABLE_NAME))
                .build();
        this.db_adapter = DynamoDBAdapter.getInstance();
        this.client = this.db_adapter.getDbClient();

        // create the mapper with config
        this.mapper = this.db_adapter.createDbMapper(mapperConfig);
    }

    public Boolean ifTableExists() {
        return this.client.describeTable(PRODUCTS_TABLE_NAME).getTable().getTableStatus().equals("ACTIVE");
    }

    public List<StatsDto> listStats() {
        DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
        List<StatsEntity> results = this.mapper.scan(StatsEntity.class, scanExp);
        List<StatsDto> statsDtoList = new ArrayList<>();
        for (StatsEntity p : results) {
            System.out.println("Stats - list(): " + p.toString());
            statsDtoList.add(StatsBuilder.convertirADominio(p));
        }
        return statsDtoList;
    }

    @Override
    public StatsDto getStatByTipo(String tipo) {
        StatsEntity stats = null;

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(tipo));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :val1")
                .withExpressionAttributeValues(eav);

        List<StatsEntity> statsResult = mapper.scan(StatsEntity.class, scanExpression);
        if (statsResult.size() > 0) {
            stats = statsResult.get(0);
            System.out.println("Stats - get(): " + stats.toString());
        } else {
            System.out.println("Stats - get(): Not Found.");
        }
        return stats!=null?StatsBuilder.convertirADominio(stats):null;
    }

    @Override
    public StatsDto addStat(StatsDto statsDto) {
        StatsEntity statsEntity = StatsBuilder.convertirAEntity(statsDto);
        System.out.println("Stats - save(): " + statsEntity.toString());
        this.mapper.save(statsEntity);
        return StatsBuilder.convertirADominio(statsEntity);
    }
}
