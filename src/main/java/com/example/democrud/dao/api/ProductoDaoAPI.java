package com.example.democrud.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.example.democrud.model.producto;

public interface ProductoDaoAPI extends CrudRepository<producto, Long> {

}
