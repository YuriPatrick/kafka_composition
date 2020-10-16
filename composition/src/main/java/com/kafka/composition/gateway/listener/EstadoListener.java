package com.kafka.composition.gateway.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.composition.gateway.json.EstadoJson;
import com.kafka.composition.gateway.json.EstadoList;
import com.kafka.composition.service.estado.ConsultarEstadoService;

/*
 * CLASSE DE SERVIÇO RESPONSÁVEL PELO RECEBIMENTO DO TOPICO ENVIADO PELA API
 */
@Service
public class EstadoListener {

	@Autowired
	private ConsultarEstadoService consultarEstadoService;

	@KafkaListener(topics = "${kafka.topic.request-topic}")
	public Message<String> execute(@Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTo,
			@Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws JsonProcessingException {

		long tempoInicial = System.currentTimeMillis();

		ObjectMapper mapper = new ObjectMapper();

		List<EstadoJson> listEstados = consultarEstadoService.execute();

		String jsonReturn = mapper.writeValueAsString(EstadoList.builder().list(listEstados).build());

		System.out.printf("Dentro do Listener de estado: %.3f ms%n",
				(System.currentTimeMillis() - tempoInicial) / 1000d);

		return MessageBuilder.withPayload(jsonReturn).setHeader(KafkaHeaders.TOPIC, replyTo)
				.setHeader(KafkaHeaders.CORRELATION_ID, correlation).build();

	}

}
