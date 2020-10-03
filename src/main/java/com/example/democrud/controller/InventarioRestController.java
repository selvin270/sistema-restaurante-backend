package com.example.democrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.democrud.model.inventario;
import com.example.democrud.service.api.InventarioServiceAPI;

//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200" ,maxAge = 3600)
@RestController
@RequestMapping(value = "/inventario")
public class InventarioRestController {

	@Autowired
	private InventarioServiceAPI inventarioServiceAPI;
	
	//@GetMapping(value = "/all")
	@GetMapping
	public List<inventario> getAll() {
		return inventarioServiceAPI.getAll();
	}
	
	@GetMapping(value = "/{id}")
	//@GetMapping
	public inventario find(@PathVariable Long id) {
		return inventarioServiceAPI.get(id);
	}
	
	//@PostMapping(value = "/save")
	@PostMapping
	public ResponseEntity<inventario> save(@RequestBody inventario inventario) {
		inventario obj = inventarioServiceAPI.save(inventario);
		return new ResponseEntity<inventario>(obj, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	//@PutMapping
	public inventario edit(@RequestBody inventario e, @PathVariable("id") Long id) {
		e.setId_inventario(id);
		return inventarioServiceAPI.save(e);
	}
	
	@DeleteMapping(value = "/{id}")
	//@DeleteMapping
	public ResponseEntity<inventario> delete(@PathVariable Long id) {
		inventario inventario = inventarioServiceAPI.get(id);
		if (inventario != null) {
			inventarioServiceAPI.delete(id);
		}else {
			return new ResponseEntity<inventario>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<inventario>(inventario, HttpStatus.OK);
	}

}
