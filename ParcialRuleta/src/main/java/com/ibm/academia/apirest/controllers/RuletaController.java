package com.ibm.academia.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.dto.RuletaDTO;
import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.services.IRuletaService;

@RestController
@RequestMapping("/ruleta")
public class RuletaController {
	
	@Autowired
	private IRuletaService ruletaService;
	
	/**
	 * Punto 1
	 * Endpoint de creación de nuevas ruletas que devuelva el id de la nueva ruleta creada 
	 * @param Ruleta a guardar
	 * @return Regresa el id de la nueva ruleta
	 * @author CUVH - 20/12/2021
	 */
	@PostMapping("/guardar")
	public ResponseEntity<?> crearRuleta(@RequestBody Ruleta ruleta){
		Ruleta ruletaGuardada = ruletaService.guardar(ruleta);
		return new ResponseEntity<Integer>(ruletaGuardada.getId(), HttpStatus.OK);
	}
	
	/**
	 * Punto 2
	 * Endpoint de apertura de ruleta que permita las posteriores peticiones de apuestas
	 * @param id Parametro con el que será encontrada la ruleta
	 * @return Retorna si fue correcta la apertura o no
	 * @author CUVH - 20/12/2021
	 */
	@PutMapping("/apertura/id/{id}")
	public ResponseEntity<?> aperturaRuleta(@PathVariable Integer id){
		if(ruletaService.aperturaRuleta(id))
			return new ResponseEntity<String>("Apertura correcta", HttpStatus.OK);
		else
			throw new BadRequestException("Esta apuesta ya se realizó");
	}
	
	/**
	 * Punto 3
	 * Endpoint de apuesta a un número
	 * @param id Para encontrar la ruleta
	 * @param numero El numero apostado
	 * @param cantidad La cantidad apostaa
	 * @return Retorna si fue correcta la apuesta o no
	 * @author CUVH - 20/12/2021
	 */
	@PutMapping("/apuesta")
	public ResponseEntity<?> apuestaNumero(@RequestParam Integer id, @RequestParam Integer numero, @RequestParam Double cantidad){
		if(ruletaService.apuesta(id, numero, cantidad))
			return new ResponseEntity<String>("Apuesta realizada con exito", HttpStatus.OK);
		else
			throw new BadRequestException("Esta ruleta no se ha abierto");
	}
	
	/**
	 * Punto 4
	 * Endpoint que cierra las apuestas
	 * @param id
	 * @return Retorna el estado de la apuesta que se cerró
	 * @author CUVH - 20/12/2021
	 */
	@PutMapping("/cerrar/id/{id}")
	public ResponseEntity<?> cierreApuesta(@PathVariable Integer id){
		RuletaDTO ruletaDTO = ruletaService.cierreRuleta(id);
		 return new ResponseEntity<RuletaDTO>(ruletaDTO, HttpStatus.OK);
	}
	
	/**
	 * Punto 5
	 * Endpoint de listado de ruletas creadas con sus estados
	 * @return Retorna una lista con todas las ruletas existentes
	 * @NotFoundException En caso de que no existan ruletas
	 * @author CUVH - 20/12/2021
	 */
	@GetMapping("/todas")
	public ResponseEntity<?> buscarTodas(){
		List<Ruleta> ruletas = (List<Ruleta>) ruletaService.buscarTodos();
		if(ruletas.isEmpty())
			throw new NotFoundException("No existen ruletas");
		return new ResponseEntity<List<Ruleta>>(ruletas, HttpStatus.OK);
	}
	

}
