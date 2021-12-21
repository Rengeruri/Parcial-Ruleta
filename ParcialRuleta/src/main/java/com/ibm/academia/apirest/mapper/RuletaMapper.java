package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.models.dto.RuletaDTO;
import com.ibm.academia.apirest.models.entities.Ruleta;

public class RuletaMapper {
	public static RuletaDTO mapRuleta(Ruleta ruleta) {
		RuletaDTO ruletaDTO = new RuletaDTO();
		ruletaDTO.setNumeroApostado(ruleta.getNumeroApostado());
		ruletaDTO.setNumeroGanador(ruleta.getNumeroGanador());
		ruletaDTO.setEstadoApuesta(ruleta.getEstadoApuesta());
		ruletaDTO.setGanancia(ruleta.getApuestaCierre() - ruleta.getApuestaApertura());
		return ruletaDTO;
	}
}
