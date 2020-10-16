package com.kafka.serivce.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<EstadoJson> list;
}
