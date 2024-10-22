package com.sazonysabor.api.mesa;

public class DTOMesaReq {
	// attributes
	private int capacidad;
	private boolean estado;
	// constructors
	public DTOMesaReq() {
	}
	public DTOMesaReq(int capacidad, boolean estado) {
		this.capacidad = capacidad;
		this.estado = estado;
	}
	// getters and setters
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
