package com.mercadolibre.infrastructure.controller;

import com.mercadolibre.application.handler.HandlerSearchAgent;
import com.mercadolibre.domain.excepcion.DnaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MutantHandlerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void mutant_test_ok() throws DnaException {
        long inicio = System.currentTimeMillis();
        String[] dna = {"ATGCAA","GCATGA","ATGCAA","GCATGA","ATGCAT","GCATGC"};
        HandlerSearchAgent searchAgent = new HandlerSearchAgent();
        Assert.assertEquals(searchAgent.isMutant(dna),true);
    }

    @Test
    public void human_test_ok() throws DnaException {
        long inicio = System.currentTimeMillis();
        String[] dna = {"ATGCAT","GCATGC","ATGCAT","GCATGC","ATGCAT","GCATGC"};
        HandlerSearchAgent searchAgent = new HandlerSearchAgent();
        Assert.assertEquals(searchAgent.isMutant(dna),false);
    }

    @Test(expected = Exception.class)
    public void invalid_dna() throws Exception{
        long inicio = System.currentTimeMillis();
        String[] dna = {"015855","GCATGC","ATGCAT","GCATGC","ATGCAT","GCATGC"};
        HandlerSearchAgent searchAgent = new HandlerSearchAgent();
        boolean error = false;
        searchAgent.isMutant(dna);


    }

}