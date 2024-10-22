package com.sazonysabor.api.pago;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOPagoReq {
	// attributes
	private double monto;
	private LocalDateTime fecha;
	@JsonProperty("modo")
	private String modoPago;
	// constructors
	public DTOPagoReq() {
	}
	public DTOPagoReq(double monto, LocalDateTime fecha, String modoPago) {
		this.monto = monto;
		this.fecha = fecha;
		this.modoPago = modoPago;
	}
	// getters and setters
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
