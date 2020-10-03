package com.example.democrud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.democrud.commons.GenericServiceImpl;
import com.example.democrud.dao.api.InventarioDaoAPI;
import com.example.democrud.model.inventario;
import com.example.democrud.service.api.InventarioServiceAPI;

@Service
public class InventarioServiceImpl extends GenericServiceImpl<inventario, Long> implements InventarioServiceAPI {

	@Autowired
	private InventarioDaoAPI inventarioDaoAPI;
	
	@Override
	public CrudRepository<inventario, Long> getDao() {
		return inventarioDaoAPI;
	}

}
