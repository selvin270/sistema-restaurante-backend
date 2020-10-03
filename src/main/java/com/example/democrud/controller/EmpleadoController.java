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
public class EmpleadoController {
	
	@Autowired
	private EmpleadoServiceAPI empleadoServiceAPI;
	
	@RequestMapping("empleados")
	public String index(Model model) {
		model.addAttribute("list", empleadoServiceAPI.getAll());
		return "mostrar_empleados";
	}
	
	@GetMapping("/editar_empleado/{id}")
	public String showSave(@PathVariable("id") Long id , Model model) {
		if(id != null && id != 0) {
			model.addAttribute("empleado", empleadoServiceAPI.get(id));
		}else {
			model.addAttribute("empleado", new empleado());
		}
		return "nuevo_empleado";
	}
	
	@PostMapping("/nuevo_empleado")
	public String save(empleado empleado, Model model) {
		empleadoServiceAPI.save(empleado);
		return "redirect:/empleados";
	}
	
	@GetMapping("/borrar_empleado/{id}")
	public String delete(@PathVariable Long id, Model model) {
		empleadoServiceAPI.delete(id);
		
		return "redirect:/empleados";
	}
	
	

}
