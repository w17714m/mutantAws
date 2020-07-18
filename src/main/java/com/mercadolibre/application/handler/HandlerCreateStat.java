package com.mercadolibre.application.handler;

import com.mercadolibre.domain.ResultStats;
import com.mercadolibre.domain.StatsDto;
import com.mercadolibre.domain.excepcion.StatsException;
import com.mercadolibre.domain.servicio.stats.ServiceNewStats;

public class HandlerCreateStat {
    private final ServiceNewStats serviceNewStats;


    public HandlerCreateStat(ServiceNewStats serviceNewStats) {
        this.serviceNewStats = serviceNewStats;
    }
    public HandlerCreateStat() {
        this.serviceNewStats = new ServiceNewStats();
    }

    public ResultStats getStats() throws StatsException {
        return this.serviceNewStats.getStats();
    }

    public StatsDto addStats(String tipo) throws StatsException {
        return this.serviceNewStats.addStat(tipo);
    }

}
