package com.ibm.academia.apirest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Ruleta;

@Repository
public interface RuletaRepository extends CrudRepository<Ruleta, Integer>{
	
}
