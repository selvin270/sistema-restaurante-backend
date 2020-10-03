package com.example.democrud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.democrud.commons.GenericServiceImpl;
import com.example.democrud.dao.api.ProductoDaoAPI;
import com.example.democrud.model.producto;
import com.example.democrud.service.api.ProductoServiceAPI;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<producto, Long> implements ProductoServiceAPI {

	@Autowired
	private ProductoDaoAPI productoDaoAPI;
	
	@Override
	public CrudRepository<producto, Long> getDao() {
		return productoDaoAPI;
	}

}
