package com.sazonysabor.api.mesa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesas")
public class EntidadMesa {
	// attributes
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int capacidad;
	private boolean estado;
	// constructors
	public EntidadMesa() {
	}
	public EntidadMesa(Long id, int capacidad, boolean estado) {
		this.id = id;
		this.capacidad = capacidad;
		this.estado = estado;
	}
	// getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
