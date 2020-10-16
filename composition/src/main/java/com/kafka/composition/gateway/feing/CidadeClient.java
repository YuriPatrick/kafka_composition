package com.kafka.composition.gateway.feing;

import java.util.List;

import com.kafka.composition.gateway.json.CidadeJson;

import feign.Param;
import feign.RequestLine;

//INTERFACE PARA CONSUMIR API EXTERNA
public interface CidadeClient {

	@RequestLine("GET /api/v1/localidades/estados/{UF}/municipios")
	List<CidadeJson> get(@Param("UF") String uf);
}
