package com.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.dto.Ville;

public interface VilleDAO {
	public List<Ville> findAllVilles();
	
	public List<Ville> findVilleByCodeCommune(String codeCommune);
	
	public String setVille(String codeCommune, String nom, String codePostal, String libelle, String ligne, String latitude, String longitude);

	public String updateVille(@RequestParam(required = true, value="codeCommune") String codeCommune,
            @RequestParam(required = true, value="nomCommune") String nomCommune,
            @RequestParam(required = true, value="codePostal") String codePostal,
            @RequestParam(required = true, value="libelleAcheminement") String libelleAcheminement,
            @RequestParam(required = true, value="ligne") String ligne,
            @RequestParam(required = true, value="latitude") String latitude,
            @RequestParam(required = true, value="longitude") String longitude);
	
	public String removeVille(String codeCommune);
}
