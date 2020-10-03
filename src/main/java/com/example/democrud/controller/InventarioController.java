package com.example.democrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.democrud.model.inventario;
import com.example.democrud.service.api.InventarioServiceAPI;

@Controller
@RequestMapping
public class InventarioController {
	
	@Autowired
	private InventarioServiceAPI inventarioServiceAPI;
	
	@RequestMapping("inventarios")
	public String index(Model model) {
		model.addAttribute("list", inventarioServiceAPI.getAll());
		return "mostrar_inventarios";
	}
	
	@GetMapping("/editar_inventario/{id}")
	public String showSave(@PathVariable("id") Long id , Model model) {
		if(id != null && id != 0) {
			model.addAttribute("inventario", inventarioServiceAPI.get(id));
		}else {
			model.addAttribute("inventario", new inventario());
		}
		return "nuevo_inventario";
	}
	
	@PostMapping("/nuevo_inventario")
	public String save(inventario inventario, Model model) {
		inventarioServiceAPI.save(inventario);
		return "redirect:/inventarios";
	}
	
	@GetMapping("/borrar_inventario/{id}")
	public String delete(@PathVariable Long id, Model model) {
		inventarioServiceAPI.delete(id);
		
		return "redirect:/inventarios";
	}
	
	

}
