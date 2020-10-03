package com.example.democrud.dao.api;

import org.springframework.data.repository.CrudRepository;

import com.example.democrud.model.empleado;

public interface EmpleadoDaoAPI extends CrudRepository<empleado, Long> {

}
