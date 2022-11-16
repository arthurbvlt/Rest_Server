package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDAO {
	public List<Ville> findAllVilles();
	
	public List<Ville> findVilleByPostalCode(String postalCode);
	
	public String setVille(String codeCommune, String nom, String codePostal, String libelle, String ligne, String latitude, String longitude);

	public String updateVille(String codeCommune, String nom);
	
	public String removeVille(String codeCommune);
}
