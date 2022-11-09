package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
	
//	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@GetMapping(value = "/test")
	@ResponseBody
	public String get() {
		System.out.println("get");
		
		return "Index";
	}
}
