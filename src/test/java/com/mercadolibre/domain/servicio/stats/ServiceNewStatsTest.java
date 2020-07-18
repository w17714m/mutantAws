package com.mercadolibre.domain.servicio.stats;

import com.mercadolibre.application.handler.HandlerCreateStat;
import com.mercadolibre.domain.ResultStats;
import com.mercadolibre.domain.StatsDto;
import com.mercadolibre.domain.excepcion.StatsException;
import com.mercadolibre.domain.repository.RepositoryStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceNewStatsTest {

    public static final String RETURN_STATS = "{}";
    public static final StatsDto MUTANT = new StatsDto("1", "mutant", 1f);
    public static final ResultStats STATS_RESULT = new ResultStats(1,1,1);
    RepositoryStats repositoryStats;
    ServiceNewStats serviceNewStats;
    @Before
    public void init() throws StatsException {
        repositoryStats =  mock(RepositoryStats.class );
        when(repositoryStats.addStat(any(StatsDto.class))).thenReturn(MUTANT);
        when(repositoryStats.getStatByTipo("mutant")).thenReturn(MUTANT);
        when(repositoryStats.getStatByTipo("human")).thenReturn(null);
        serviceNewStats = new ServiceNewStats(repositoryStats);
    }


    @Test
    public void getStatByTipo() {
        Assert.assertEquals(serviceNewStats.getStatByTipo("mutant"),MUTANT);
    }

    @Test
    public void addStat() {
        Assert.assertEquals(serviceNewStats.addStat("mutant"),MUTANT);
    }

    @Test
    public void addStat_flow2() {
        Assert.assertEquals(serviceNewStats.addStat("human"),MUTANT);
    }

    @Test
    public void getStats() throws StatsException {
        Assert.assertNotNull(serviceNewStats.getStats());
    }
}