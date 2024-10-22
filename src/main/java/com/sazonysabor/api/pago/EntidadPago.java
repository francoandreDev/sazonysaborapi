package com.sazonysabor.api.pago;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class EntidadPago {
	// attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double monto;
	private LocalDateTime fecha;
	@JsonProperty("modo")
	private String modoPago;
	// constructors
	public EntidadPago() {
	}
	public EntidadPago(Long id, double monto, LocalDateTime fecha, String modoPago) {
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
