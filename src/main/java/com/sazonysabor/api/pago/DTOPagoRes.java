package com.sazonysabor.api.pago;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOPagoRes {
	// attributes
	private Long id;
	private double monto;
	private LocalDateTime fecha;
	@JsonProperty("modo")
	private String modoPago;
	// constructors
	public DTOPagoRes() {
	}
	public DTOPagoRes(Long id, double monto, LocalDateTime fecha, String modoPago) {
		this.id = id;
		this.monto = monto;
		this.fecha = fecha;
		this.modoPago = modoPago;
	}
	// getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getModoPago() {
		return modoPago;
	}
	public void setModoPago(String modoPago) {
		this.modoPago = modoPago;
	}
}
