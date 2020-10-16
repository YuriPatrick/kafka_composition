package com.kafka.composition.gateway.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.composition.gateway.json.CidadeJson;
import com.kafka.composition.gateway.json.EstadoJson;
import com.kafka.composition.service.cidade.ConsultarCidadeByIdService;
import com.kafka.composition.service.estado.ConsultarEstadoService;

/*
 * CLASSE DA API RESPONSÁVEL PELA EXECUÇÃO DOS ENDPOINT
 */
@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private ConsultarEstadoService consultarEstadoService;

	@Autowired
	private ConsultarCidadeByIdService ConsultarCidadeByIdService;

	@GetMapping("/")
	public List<EstadoJson> consultarEstado() {
		return consultarEstadoService.execute();
	}

	@GetMapping("/{id}/cidades/")
	public List<CidadeJson> consultarCidades(@PathVariable("id") String estados) {
		return ConsultarCidadeByIdService.execute(estados);
	}
	
}
