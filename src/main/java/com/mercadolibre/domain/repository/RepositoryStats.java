package com.mercadolibre.domain.repository;

import com.mercadolibre.domain.StatsDto;

import java.util.List;

public interface RepositoryStats {
    /**
     * List Stats dto
     * @return
     */
    List<StatsDto> listStats();

    /**
     * get Stats by Tipo
     * @param tipo
     * @return
     */
    StatsDto getStatByTipo(String tipo);

    /**
     * add Stats
     * @param Stats
     * @return
     */
    StatsDto addStat(StatsDto statsDto);
}
