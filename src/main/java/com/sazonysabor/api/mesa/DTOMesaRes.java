package com.sazonysabor.api.mesa;

public class DTOMesaRes {
	// attributes
	private Long id;
	private int capacidad;
	private boolean estado;
	// constructors
	public DTOMesaRes() {
	}
	public DTOMesaRes(Long id, int capacidad, boolean estado) {
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
