package com.example.democrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.democrud.model.empleado;
import com.example.democrud.service.api.EmpleadoServiceAPI;

@Controller
@RequestMapping
public class InicioController {
	
	@Autowired
	private EmpleadoServiceAPI empleadoServiceAPI;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list", empleadoServiceAPI.getAll());
		return "index";
	}
	
	

}
