package com.kafka.composition.service.estado;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kafka.composition.gateway.feing.EstadoClient;
import com.kafka.composition.gateway.json.EstadoJson;

import feign.Feign;
import feign.gson.GsonDecoder;

//CLASSE DE SERVIÇO PARA A CONSULTA DA API EXTERNA
@Service
public class ConsultarEstadoService {

  public List<EstadoJson> execute() {
        long tempoInicial = System.currentTimeMillis();

        EstadoClient estadoClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(EstadoClient.class, "https://servicodados.ibge.gov.br");

        List<EstadoJson> list =  estadoClient.get();
        
        System.out.printf("Dentro do serviço de estado: %.3f ms%n", (System.currentTimeMillis() - tempoInicial) / 1000d);
        return list;
    }
}
