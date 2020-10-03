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

import com.example.democrud.model.empleado;
import com.example.democrud.model.producto;
import com.example.democrud.service.api.ProductoServiceAPI;

@RestController
@RequestMapping(value = "/producto")
@CrossOrigin("*")
public class ProductoRestController {

	@Autowired
	private ProductoServiceAPI productoServiceAPI;

	@GetMapping
	public List<producto> getAll() {
		return productoServiceAPI.getAll();
	}
	
	@GetMapping(value = "/{id}")
	public producto find(@PathVariable Long id) {
		return productoServiceAPI.get(id);
	}

	@PostMapping
	public ResponseEntity<producto> save(@RequestBody producto producto) {
		producto obj = productoServiceAPI.save(producto);
		return new ResponseEntity<producto>(obj, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	//@PutMapping
	public producto edit(@RequestBody producto p, @PathVariable("id") Long id) {
		p.setId_producto(id);
		return productoServiceAPI.save(p);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<producto> delete(@PathVariable Long id) {
		producto producto = productoServiceAPI.get(id);
		if (producto != null) {
			productoServiceAPI.delete(id);
		}else {
			return new ResponseEntity<producto>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<producto>(producto, HttpStatus.OK);
	}

}
