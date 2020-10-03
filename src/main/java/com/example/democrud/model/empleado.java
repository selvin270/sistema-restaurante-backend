package com.example.democrud.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_empleado;
	
	@Column
	private int id_tienda;
	
	@Column
	private String p_nombre;
	
	@Column
	private String s_nombre;
	
	@Column
	private String p_apellido;
	
	@Column
	private String s_apellido;

	public Long getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(Long id_empleado) {
		this.id_empleado = id_empleado;
	}

	public int getId_tienda() {
		return id_tienda;
	}

	public void setId_tienda(int id_tienda) {
		this.id_tienda = id_tienda;
	}

	public String getP_nombre() {
		return p_nombre;
	}

	public void setP_nombre(String p_nombre) {
		this.p_nombre = p_nombre;
	}

	public String getS_nombre() {
		return s_nombre;
	}

	public void setS_nombre(String s_nombre) {
		this.s_nombre = s_nombre;
	}

	public String getP_apellido() {
		return p_apellido;
	}

	public void setP_apellido(String p_apellido) {
		this.p_apellido = p_apellido;
	}

	public String getS_apellido() {
		return s_apellido;
	}

	public void setS_apellido(String s_apellido) {
		this.s_apellido = s_apellido;
	}

	

	
}
