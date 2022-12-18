package com.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO{
	
	@Autowired
	private VilleDAO villeDAO;
	
	public List<Ville> getInfoVilles(String codeCommune) {
		List<Ville> listVille = null;
		if (codeCommune != null) {
			listVille = villeDAO.findVilleByCodeCommune(codeCommune);
		}
		else {			
			listVille = villeDAO.findAllVilles();
		}
		return listVille;			
	}
	
	public String addVille(String codeCommune, String nom, String codePostal, String libelle, String ligne, String latitude, String longitude) {
		return villeDAO.setVille(codeCommune, nom, codePostal, libelle, ligne, latitude, longitude);
	}
	
	public String updateVille(@RequestParam(required = true, value="codeCommune") String codeCommune,
            @RequestParam(required = true, value="nomCommune") String nomCommune,
            @RequestParam(required = true, value="codePostal") String codePostal,
            @RequestParam(required = true, value="libelleAcheminement") String libelleAcheminement,
            @RequestParam(required = true, value="ligne") String ligne,
            @RequestParam(required = true, value="latitude") String latitude,
            @RequestParam(required = true, value="longitude") String longitude) {
		return villeDAO.updateVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
	}
	
	public String removeVille(String codeCommune) {
		return villeDAO.removeVille(codeCommune);
	}
}
