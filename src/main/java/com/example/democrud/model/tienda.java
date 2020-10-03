package com.example.democrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class tienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tienda;
	
	@Column
	private String dir_tienda;
	
	@Column
	private String tel_tienda;

	public Long getId_tienda() {
		return id_tienda;
	}

	public void setId_tienda(Long id_tienda) {
		this.id_tienda = id_tienda;
	}

	public String getDir_tienda() {
		return dir_tienda;
	}

	public void setDir_tienda(String dir_tienda) {
		this.dir_tienda = dir_tienda;
	}

	public String getTel_tienda() {
		return tel_tienda;
	}

	public void setTel_tienda(String tel_tienda) {
		this.tel_tienda = tel_tienda;
	}

	

	

	
}
