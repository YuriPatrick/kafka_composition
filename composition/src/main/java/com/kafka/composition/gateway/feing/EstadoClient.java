package com.kafka.composition.gateway.feing;

import java.util.List;

import com.kafka.composition.gateway.json.EstadoJson;

import feign.RequestLine;

//INTERFACE PARA CONSUMIR API EXTERNA
public interface EstadoClient {
	
	@RequestLine("GET /api/v1/localidades/estados")
	List<EstadoJson> get();

}
