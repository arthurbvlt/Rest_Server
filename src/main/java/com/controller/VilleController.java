package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {
	
	@Autowired
	VilleBLO villeBLOService;
	
	@GetMapping(value = "/ville")
	@ResponseBody
	public List<Ville> getVille(@RequestParam(required = false, value="codeCommune") String codeCommune) {
		return villeBLOService.getInfoVilles(codeCommune);
	}
	
	@PostMapping(value = "ville/postVille")
	@ResponseBody 
	public String postVille(@RequestParam(required = true, value="codeCommune") String codeCommune,
			@RequestParam(required = true, value="nom") String nom,
			@RequestParam(required = true, value="codePostal") String codePostal,
			@RequestParam(required = true, value="libelle") String libelle,
			@RequestParam(required = false, value="ligne") String ligne,
			@RequestParam(required = true, value="latitude") String latitude,
			@RequestParam(required = true, value="longitude") String longitude) {
		
			return villeBLOService.addVille(codeCommune, nom, codePostal, libelle, ligne, latitude, longitude);
	}
	
	@PutMapping(value = "ville/putVille")
	@ResponseBody 
	public String putVille(@RequestParam(required = true, value="codeCommune") String codeCommune,
	                       @RequestParam(required = true, value="nomCommune") String nomCommune,
	                       @RequestParam(required = true, value="codePostal") String codePostal,
	                       @RequestParam(required = true, value="libelleAcheminement") String libelleAcheminement,
	                       @RequestParam(required = true, value="ligne") String ligne,
	                       @RequestParam(required = true, value="latitude") String latitude,
	                       @RequestParam(required = true, value="longitude") String longitude) {
	    return villeBLOService.updateVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
	}
	
	@DeleteMapping(value = "ville/deleteVille")
	@ResponseBody 
	public String deleteVille(@RequestParam(required = true, value="codeCommune") String codeCommune) {
			return villeBLOService.removeVille(codeCommune);
	}	
	

}
