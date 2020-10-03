package com.example.democrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.democrud.model.producto;
import com.example.democrud.service.api.ProductoServiceAPI;

@Controller
@RequestMapping
public class ProductoController {
	
	@Autowired
	private ProductoServiceAPI productoServiceAPI;
	
	@RequestMapping("productos")
	public String index(Model model) {
		model.addAttribute("list", productoServiceAPI.getAll());
		return "mostrar_productos";
	}
	
	@GetMapping("/editar_producto/{id}")
	public String showSave(@PathVariable("id") Long id , Model model) {
		if(id != null && id != 0) {
			model.addAttribute("producto", productoServiceAPI.get(id));
		}else {
			model.addAttribute("producto", new producto());
		}
		return "nuevo_producto";
	}
	
	@PostMapping("/nueva_producto")
	public String save(producto producto, Model model) {
		productoServiceAPI.save(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/borrar_producto/{id}")
	public String delete(@PathVariable Long id, Model model) {
		productoServiceAPI.delete(id);
		
		return "redirect:/productos";
	}
	
	

}
