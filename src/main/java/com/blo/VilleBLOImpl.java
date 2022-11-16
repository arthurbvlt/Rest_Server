package com.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO{
	
	@Autowired
	private VilleDAO villeDAO;
	
	public List<Ville> getInfoVilles(String codePostal) {
		List<Ville> listVille = null;
		if (codePostal != null) {
			listVille = villeDAO.findVilleByPostalCode(codePostal);
		}
		else {			
			listVille = villeDAO.findAllVilles();
		}
		return listVille;			
	}
	
	public String addVille(String codeCommune, String nom, String codePostal, String libelle, String ligne, String latitude, String longitude) {
		return villeDAO.setVille(codeCommune, nom, codePostal, libelle, ligne, latitude, longitude);
	}
	
	public String updateVille(String codeCommune, String nom) {
		return villeDAO.updateVille(codeCommune, nom);
	}
	
	public String removeVille(String codeCommune) {
		return villeDAO.removeVille(codeCommune);
	}
}
