package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.mapper.RuletaMapper;
import com.ibm.academia.apirest.models.dto.RuletaDTO;
import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.repositories.RuletaRepository;

@Service
public class RuletaServiceImp<E, R extends CrudRepository<E, Integer>> implements IRuletaService{

	@Autowired
	protected final RuletaRepository repository;
	public RuletaServiceImp(RuletaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Ruleta> bucarPorId(Integer id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Ruleta guardar(Ruleta ruleta) {
		return repository.save(ruleta);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Ruleta> buscarTodos() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public Boolean aperturaRuleta(Integer id) {
		Optional<Ruleta> oRuleta = repository.findById(id);
		if(!oRuleta.isPresent())
			throw new NotFoundException(String.format("No se encontró ruleta con id %d", id));
		Ruleta ruleta = oRuleta.get();
		if(ruleta.getEstadoApuesta().equals("Ganada") || ruleta.getEstadoApuesta().equals("Perdida"))
			return false;
		ruleta.setEstadoRuleta(true);
		ruleta.setEstadoApuesta("Abierta");
		repository.save(ruleta);
		return true;
	}

	@Override
	public Boolean apuesta(Integer id, Integer numeroApostado, Double cantidad) {
		Optional<Ruleta> oRuleta = repository.findById(id);
		if(!oRuleta.isPresent())
			throw new NotFoundException(String.format("No se encontró ruleta con id %d", id));
		Ruleta ruleta = oRuleta.get();
		if(!ruleta.getEstadoRuleta())
			return false;
		ruleta.setNumeroApostado(numeroApostado);
		ruleta.setApuestaApertura(cantidad);
		ruleta.setEstadoApuesta("Apostado");
		repository.save(ruleta);
		return true;
	}

	@Override
	public RuletaDTO cierreRuleta(Integer id) {
		Optional<Ruleta> oRuleta = repository.findById(id);
		if(!oRuleta.isPresent())
			throw new NotFoundException(String.format("No se encontró ruleta con id %d", id));
		Ruleta ruleta = oRuleta.get();
		Integer numeroGanador = (int) (Math.random() * 36);
		ruleta.setNumeroGanador(numeroGanador);
		if(ruleta.getNumeroApostado() == numeroGanador) {
			ruleta.setApuestaCierre(ruleta.getApuestaApertura() * 2);
			ruleta.setEstadoApuesta("Ganada");
		}else {
			ruleta.setApuestaCierre(0.0);
			ruleta.setEstadoApuesta("Perdida");
		}
		repository.save(ruleta);
		RuletaDTO ruletaDTO = RuletaMapper.mapRuleta(ruleta);
		return ruletaDTO;
	}


}
