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
import com.kafka.composition.gateway.json.CidadeJson;
import com.kafka.composition.gateway.json.CidadeList;
import com.kafka.composition.gateway.json.EstadoRequestTopicJson;
import com.kafka.composition.service.cidade.ConsultarCidadeByIdService;

/*
 * CLASSE DE SERVIÇO RESPONSÁVEL PELO RECEBIMENTO DO TOPICO ENVIADO PELA API
 */
@Service
public class CidadeListener {

    @Autowired
    private ConsultarCidadeByIdService consultarCidadeByIdService;

    @KafkaListener(topics = "${kafka.topic.request-topic-cidade}")
    public Message<String> execute(String in, @Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTo,
                                   @Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        EstadoRequestTopicJson json = mapper.readValue(in, EstadoRequestTopicJson.class);

        List<CidadeJson> list = consultarCidadeByIdService.execute(json.getUf());

        String jsonReturn = mapper.writeValueAsString(CidadeList.builder().list(list).build());

        return MessageBuilder.withPayload(jsonReturn)
                .setHeader(KafkaHeaders.TOPIC, replyTo)
                .setHeader(KafkaHeaders.CORRELATION_ID, correlation)
                .build();

    }

}
