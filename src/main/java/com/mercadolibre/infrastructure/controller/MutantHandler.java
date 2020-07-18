package com.mercadolibre.infrastructure.controller;

import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.application.handler.HandlerCreateStat;
import com.mercadolibre.application.handler.HandlerSearchAgent;
import com.mercadolibre.domain.excepcion.DnaException;
import com.mercadolibre.infrastructure.entity.Response;
import com.mercadolibre.infrastructure.entity.ApiGatewayResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class MutantHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	public HandlerCreateStat handlerCreateStat;
	public HandlerSearchAgent handlerSearchAgent;

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {


		try {
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
			if(body.get("dna").isArray()){
				List<String> dnaArray = new ArrayList<>();
				for (final JsonNode objNode : body.get("dna")) {
					dnaArray.add(objNode.toString().replaceAll("\"",""));
				}
				handlerCreateStat = new HandlerCreateStat();
				handlerSearchAgent = new HandlerSearchAgent();
				String type = "";
				if(handlerSearchAgent.isMutant(dnaArray.stream().toArray(String[]::new))){
					if(!handlerSearchAgent.isAdn()){
						throw new DnaException("No es un dna válida");
					}
					type = "mutant" + body.get("dna");
					handlerCreateStat.addStats("mutant");
					return ApiGatewayResponse.builder()
							.setStatusCode(200)
							.setObjectBody(type)
							.setHeaders(Collections.singletonMap("by", "w17714m"))
							.build();
				}else{
					type = "human" + body.get("dna");
					handlerCreateStat.addStats("human");
					return ApiGatewayResponse.builder()
							.setStatusCode(403)
							.setObjectBody(type)
							.setHeaders(Collections.singletonMap("by", "w17714m"))
							.build();
				}


			}else{
				return ApiGatewayResponse.builder()
						.setStatusCode(500)
						.setObjectBody("Error")
						.setHeaders(Collections.singletonMap("by", "w17714m"))
						.build();
			}


		} catch (Exception ex) {
			Response responseBody = new Response("Error in searching dna: " +  ex.getMessage()  , input);
			return ApiGatewayResponse.builder()
					.setStatusCode(500)
					.setObjectBody(responseBody)
					.setHeaders(Collections.singletonMap("by", "w17714m"))
					.build();
		}


	}
}
