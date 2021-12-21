package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.models.dto.RuletaDTO;
import com.ibm.academia.apirest.models.entities.Ruleta;

public interface IRuletaService {
	Optional<Ruleta> bucarPorId(Integer id);
	public Ruleta guardar(Ruleta ruleta);
	public Iterable<Ruleta> buscarTodos();
	public void eliminarPorId(Integer id);
	
	public Boolean aperturaRuleta(Integer id);
	public Boolean apuesta(Integer id, Integer numeroApostado, Double cantidad);
	public RuletaDTO cierreRuleta(Integer id);
	

}
