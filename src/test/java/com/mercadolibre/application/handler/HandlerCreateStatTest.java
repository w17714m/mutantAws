package com.mercadolibre.application.handler;

import com.mercadolibre.domain.ResultStats;
import com.mercadolibre.domain.StatsDto;
import com.mercadolibre.domain.excepcion.StatsException;
import com.mercadolibre.domain.servicio.stats.ServiceNewStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class HandlerCreateStatTest {

    public static final ResultStats RETURN_STATS = new ResultStats(1,1,1);
    public static StatsDto MUTANT = new StatsDto("1", "mutant", 10f);
    ServiceNewStats serviceNewStats;
    HandlerCreateStat handlerCreateStat;

    @Before
    public void init() throws StatsException {
        serviceNewStats =  mock(ServiceNewStats.class);
        when(serviceNewStats.getStats()).thenReturn(RETURN_STATS);
        when(serviceNewStats.getStatByTipo("mutant")).thenReturn(MUTANT);
        when(serviceNewStats.addStat("mutant")).thenReturn(MUTANT);
        when(serviceNewStats.addStat("human")).thenReturn(MUTANT);
        handlerCreateStat = new HandlerCreateStat(serviceNewStats);
    }

    @Test
    public void getStats() throws StatsException {
        MUTANT.setTotal(10f);
        MUTANT.setTipo("mutant");
        MUTANT.setId("1");
        Assert.assertEquals(handlerCreateStat.getStats(), RETURN_STATS);
    }

    @Test
    public void addStats() throws StatsException {
        MUTANT.setTotal(10f);
        MUTANT.setTipo("mutant");
        MUTANT.setId("1");
        Assert.assertEquals(handlerCreateStat.addStats("mutant"),MUTANT);
    }
}