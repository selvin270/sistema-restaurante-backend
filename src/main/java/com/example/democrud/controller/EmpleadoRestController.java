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
import com.example.democrud.service.api.EmpleadoServiceAPI;

//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200" ,maxAge = 3600)
@RestController
@RequestMapping(value = "/empleado")
public class EmpleadoRestController {

	@Autowired
	private EmpleadoServiceAPI empleadoServiceAPI;
	
	//@GetMapping(value = "/all")
	@GetMapping
	public List<empleado> getAll() {
		return empleadoServiceAPI.getAll();
	}
	
	@GetMapping(value = "/{id}")
	//@GetMapping
	public empleado find(@PathVariable Long id) {
		return empleadoServiceAPI.get(id);
	}
	
	//@PostMapping(value = "/save")
	@PostMapping
	public ResponseEntity<empleado> save(@RequestBody empleado empleado) {
		empleado obj = empleadoServiceAPI.save(empleado);
		return new ResponseEntity<empleado>(obj, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	//@PutMapping
	public empleado edit(@RequestBody empleado e, @PathVariable("id") Long id) {
		e.setId_empleado(id);
		return empleadoServiceAPI.save(e);
	}
	
	@DeleteMapping(value = "/{id}")
	//@DeleteMapping
	public ResponseEntity<empleado> delete(@PathVariable Long id) {
		empleado empleado = empleadoServiceAPI.get(id);
		if (empleado != null) {
			empleadoServiceAPI.delete(id);
		}else {
			return new ResponseEntity<empleado>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<empleado>(empleado, HttpStatus.OK);
	}

}
