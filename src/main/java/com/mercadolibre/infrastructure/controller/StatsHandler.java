package com.mercadolibre.infrastructure.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mercadolibre.application.handler.HandlerCreateStat;
import com.mercadolibre.infrastructure.entity.Response;
import com.mercadolibre.infrastructure.entity.ApiGatewayResponse;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

public class StatsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {



    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        try {
            HandlerCreateStat handlerCreateStat = new HandlerCreateStat();
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(handlerCreateStat.getStats())
                    .setHeaders(Collections.singletonMap("by", "w17714m"))
                    .build();

        } catch (Exception ex) {

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();
            System.out.println(sStackTrace);
            System.out.println("Error processing stats: " + ex.getMessage());

            // send the error response back
            Response responseBody = new Response("Error in saving product: " +  sStackTrace  , input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(403)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("by", "w17714m"))
                    .build();
        }
    }
}