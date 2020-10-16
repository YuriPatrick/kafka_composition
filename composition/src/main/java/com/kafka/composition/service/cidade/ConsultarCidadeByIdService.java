package com.kafka.composition.service.cidade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kafka.composition.gateway.feing.CidadeClient;
import com.kafka.composition.gateway.json.CidadeJson;

import feign.Feign;
import feign.gson.GsonDecoder;

/*
 * CLASSE DE SERVIÇO DE CONSULTA DA API EXTERNA
 */
@Service
public class ConsultarCidadeByIdService {

	public List<CidadeJson> execute(String estado) {
		long tempoInicial = System.currentTimeMillis();

		CidadeClient cidadeClient = Feign.builder().decoder(new GsonDecoder()).target(CidadeClient.class,
				"https://servicodados.ibge.gov.br");

		List<CidadeJson> cidadeJsons = cidadeClient.get(estado);

		System.out.printf("Dentro do serviço de cidades: %.3f ms%n",
				(System.currentTimeMillis() - tempoInicial) / 1000d);

		return cidadeJsons;
	}
}
