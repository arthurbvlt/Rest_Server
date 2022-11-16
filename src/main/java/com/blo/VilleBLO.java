package com.blo;

import java.util.List;

import com.dto.Ville;

public interface VilleBLO {
	
	public List<Ville> getInfoVilles(String codePostal);
	
	public String addVille(String codeCommune, String nom, String codePostal, String libelle, String ligne, String latitude, String longitude);

	public String updateVille(String codeCommune, String nom);
	
	public String removeVille(String codeCommune);
}
