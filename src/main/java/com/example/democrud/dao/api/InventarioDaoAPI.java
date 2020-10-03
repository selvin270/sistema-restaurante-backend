package com.example.democrud.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.example.democrud.model.inventario;

public interface InventarioDaoAPI extends CrudRepository<inventario, Long> {

}
