package com.mercadolibre.domain.servicio.stats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.domain.ResultStats;
import com.mercadolibre.domain.StatsDto;
import com.mercadolibre.domain.excepcion.StatsException;
import com.mercadolibre.domain.repository.RepositoryStats;
import com.mercadolibre.infrastructure.repository.jpa.RepositoryStatsPersistence;


public class ServiceNewStats {

    public static final String MUTANT = "mutant";
    public static final String HUMAN = "human";
    public final RepositoryStats repositoryStats;

    public ServiceNewStats() {
        this.repositoryStats = new RepositoryStatsPersistence();
    }
    public ServiceNewStats(RepositoryStats repositoryStats) {
        this.repositoryStats = repositoryStats;
    }


    public StatsDto getStatByTipo(String tipo) {
       return this.repositoryStats.getStatByTipo(tipo);
    }

    public StatsDto addStat(String tipo){
        StatsDto tipoStat = this.getStatByTipo(tipo);
        if(tipoStat!=null) {
            tipoStat.setTotal(tipoStat.getTotal()+1);
        }else{
            tipoStat = new StatsDto();
            tipoStat.setTotal(1f);
            tipoStat.setTipo(tipo);
        }
        return this.repositoryStats.addStat(tipoStat);
    }

    public ResultStats getStats() throws StatsException {
        String result="na";

        StatsDto mutant = this.getStatByTipo(MUTANT);
        StatsDto human = this.getStatByTipo(HUMAN);
        float m = mutant==null?0:mutant.getTotal();
        float h = human==null?0:human.getTotal();
        ObjectMapper objectMapper = new ObjectMapper();
        ResultStats resultStats;
        try {
            resultStats = new ResultStats(m,h,m/h);
        } catch (Exception e) {
            throw new StatsException("No hay demasiados datos para hacer el calculo.");
        }
        return resultStats;
    }

}
