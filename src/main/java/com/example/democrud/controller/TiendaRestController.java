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

import com.example.democrud.model.producto;
import com.example.democrud.model.tienda;
import com.example.democrud.service.api.TiendaServiceAPI;

@RestController
@RequestMapping(value = "/tienda")
@CrossOrigin("*")
public class TiendaRestController {

	@Autowired
	private TiendaServiceAPI tiendaServiceAPI;

	@GetMapping
	public List<tienda> getAll() {
		return tiendaServiceAPI.getAll();
	}
	
	@GetMapping(value = "/{id}")
	public tienda find(@PathVariable Long id) {
		return tiendaServiceAPI.get(id);
	}

	@PostMapping
	public ResponseEntity<tienda> save(@RequestBody tienda tienda) {
		tienda obj = tiendaServiceAPI.save(tienda);
		return new ResponseEntity<tienda>(obj, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	//@PutMapping
	public tienda edit(@RequestBody tienda t, @PathVariable("id") Long id) {
		t.setId_tienda(id);
		return tiendaServiceAPI.save(t);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<tienda> delete(@PathVariable Long id) {
		tienda tienda = tiendaServiceAPI.get(id);
		if (tienda != null) {
			tiendaServiceAPI.delete(id);
		}else {
			return new ResponseEntity<tienda>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<tienda>(tienda, HttpStatus.OK);
	}

}
