package com.blo;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.dto.Ville;

public interface VilleBLO {
	
	public List<Ville> getInfoVilles(String codeCommune);
	
	public String addVille(String codeCommune, String nom, String codePostal, String libelle, String ligne, String latitude, String longitude);

	public String updateVille(@RequestParam(required = true, value="codeCommune") String codeCommune,
            @RequestParam(required = true, value="nomCommune") String nomCommune,
            @RequestParam(required = true, value="codePostal") String codePostal,
            @RequestParam(required = true, value="libelleAcheminement") String libelleAcheminement,
            @RequestParam(required = true, value="ligne") String ligne,
            @RequestParam(required = true, value="latitude") String latitude,
            @RequestParam(required = true, value="longitude") String longitude);
	
	public String removeVille(String codeCommune);
}
