package com.example.democrud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.democrud.commons.GenericServiceImpl;
import com.example.democrud.dao.api.TiendaDaoAPI;
import com.example.democrud.model.tienda;
import com.example.democrud.service.api.TiendaServiceAPI;

@Service
public class TiendaServiceImpl extends GenericServiceImpl<tienda, Long> implements TiendaServiceAPI {

	@Autowired
	private TiendaDaoAPI tiendaDaoAPI;
	
	@Override
	public CrudRepository<tienda, Long> getDao() {
		return tiendaDaoAPI;
	}

}
