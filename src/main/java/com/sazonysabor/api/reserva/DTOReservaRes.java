package com.sazonysabor.api.reserva;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOReservaRes {
	// attributes
	private Long id;
	@JsonProperty("fecha")
	private LocalDateTime fechaHora;
	@JsonProperty("personas")
	private int numeroPersonas;
	private String estado;
	// constructors
	public DTOReservaRes() {
	}
	public DTOReservaRes(Long id, LocalDateTime fechaHora, int numeroPersonas, String estado) {
		this.id = id;
		this.fechaHora = fechaHora;
		this.numeroPersonas = numeroPersonas;
		this.estado = estado;
	}
	// getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
