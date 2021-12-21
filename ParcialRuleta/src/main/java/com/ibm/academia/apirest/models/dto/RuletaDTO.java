package com.ibm.academia.apirest.models.dto;

import lombok.Data;

@Data
public class RuletaDTO {
	private Integer numeroApostado;
	private Integer numeroGanador;
	private String estadoApuesta;
	private Double ganancia;
}
