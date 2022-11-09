package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ArrayList<Ville> get(@RequestParam(required = false, value="codePostal") String codePostal) {
//		http://localhost:8181/ville?codePostal=01400
		System.out.println("get");
		System.out.println("get 2");
				
		return villeBLOService.getInfoVilles(codePostal);
	}
	
//	@PostMapping(value = "ville")
//	@ResponseBody  //pour renvoyer JSON
//	public Ville addVille()

}
