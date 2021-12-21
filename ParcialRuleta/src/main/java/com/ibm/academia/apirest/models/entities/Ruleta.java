package com.ibm.academia.apirest.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ruletas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruleta implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "estado_ruleta")
	private Boolean estadoRuleta;
	
	@Column(name = "apuesta_apertura")
	@Min(value = 1000)
	@Max(value = 10000)
	private Double apuestaApertura;
	
	@Column(name = "apuesta_cierre")
	private Double apuestaCierre;
	
	@Column(name = "numero_apostado")
	@PositiveOrZero
	@Max(value = 36)
	private Integer numeroApostado;
	
	@Column(name = "numero_ganador")
	@PositiveOrZero
	@Max(value = 36)
	private Integer numeroGanador;
	
	//@Column(name = "color")
	//private String color;
	
	@Column(name = "estado_apuesta")
	private String estadoApuesta;
	
	private static final long serialVersionUID = -8225518757194806720L;
}
