package com.kafka.serivce.gateway.resource;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.serivce.gateway.json.CidadeList;
import com.kafka.serivce.gateway.json.EstadoList;
import com.kafka.serivce.service.ConsultarCidadeService;
import com.kafka.serivce.service.ConsultarEstadoService;

/*
 * CLASSE DA API RESPONSÁVEL PELA EXECUÇÃO DOS ENDPOINT
 */
@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private ConsultarEstadoService consultarEstadoService;

	@Autowired
	private ConsultarCidadeService consultarCidadeService;

	@GetMapping("/")
	public EstadoList consultarEstados() throws InterruptedException, ExecutionException, JsonProcessingException {
		long tempoInicial = System.currentTimeMillis();

		EstadoList list = consultarEstadoService.execute();

		System.out.printf("Resource: Retorno dos estados pelo kafka: %.3f ms%n",
				(System.currentTimeMillis() - tempoInicial) / 1000d);

		return list;
	}

	@GetMapping("/{id}/cidades")
	public CidadeList consultarCidades(@PathVariable("id") String estado)
			throws InterruptedException, ExecutionException, JsonProcessingException {
		long tempoInicial = System.currentTimeMillis();

		CidadeList list = consultarCidadeService.execute(estado);

		System.out.printf("Resource: Retorno das cidades pelo kafka: %.3f ms%n",
				(System.currentTimeMillis() - tempoInicial) / 1000d);

		return list;
	}

}
