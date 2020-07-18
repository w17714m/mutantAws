package com.mercadolibre.infrastructure.builder;

import com.mercadolibre.domain.StatsDto;
import com.mercadolibre.infrastructure.entity.StatsEntity;

public class StatsBuilder {

    public static StatsDto convertirADominio(StatsEntity statsEntity) {
        StatsDto statsDto = null;
        if (statsEntity != null) {
            statsDto = new StatsDto(statsEntity.getId(), statsEntity.getTipo(), statsEntity.getTotal());
        }
        return statsDto;
    }

    public static StatsEntity convertirAEntity(StatsDto statsDto) {
        StatsEntity stats = new StatsEntity();

        if(statsDto != null){
            stats.setId(statsDto.getId());
            stats.setTipo(statsDto.getTipo());
            stats.setTotal(statsDto.getTotal());
        }
        return stats;
    }

}
