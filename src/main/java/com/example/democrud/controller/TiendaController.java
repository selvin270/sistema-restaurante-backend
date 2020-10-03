package com.example.democrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.democrud.model.tienda;
import com.example.democrud.service.api.TiendaServiceAPI;

@Controller
@RequestMapping
public class TiendaController {
	
	@Autowired
	private TiendaServiceAPI tiendaServiceAPI;
	
	@RequestMapping("tiendas")
	public String index(Model model) {
		model.addAttribute("list", tiendaServiceAPI.getAll());
		return "mostrar_tiendas";
	}
	
	@GetMapping("/editar_tienda/{id}")
	public String showSave(@PathVariable("id") Long id , Model model) {
		if(id != null && id != 0) {
			model.addAttribute("tienda", tiendaServiceAPI.get(id));
		}else {
			model.addAttribute("tienda", new tienda());
		}
		return "nueva_tienda";
	}
	
	@PostMapping("/nueva_tienda")
	public String save(tienda tienda, Model model) {
		tiendaServiceAPI.save(tienda);
		return "redirect:/tiendas";
	}
	
	@GetMapping("/borrar_tienda/{id}")
	public String delete(@PathVariable Long id, Model model) {
		tiendaServiceAPI.delete(id);
		
		return "redirect:/tiendas";
	}
	
	

}
