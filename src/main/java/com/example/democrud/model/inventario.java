package com.example.democrud.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_inventario;
	
	@Column
	private int id_tienda;
	
	@Column
	private int id_producto;
	
	@Column
	private String saldo;

	public Long getId_inventario() {
		return id_inventario;
	}

	public void setId_inventario(Long id_inventario) {
		this.id_inventario = id_inventario;
	}

	public int getId_tienda() {
		return id_tienda;
	}

	public void setId_tienda(int id_tienda) {
		this.id_tienda = id_tienda;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	
	

	

	
}
