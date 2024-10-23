package com.sazonysabor.api.reserva;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOReservaReq {
	// attributes
	@JsonProperty("fecha")
	private LocalDateTime fechaHora;
	@JsonProperty("personas")
	private int numeroPersonas;
	private String estado;
	// constructors
	public DTOReservaReq() {
	}
	public DTOReservaReq(LocalDateTime fechaHora, int numeroPersonas, String estado) {
		this.fechaHora = fechaHora;
		this.numeroPersonas = numeroPersonas;
		this.estado = estado;
	}
	// getters and setters
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	public int getNumeroPersonas() {
		return numeroPersonas;
	}
	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
