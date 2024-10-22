package com.sazonysabor.api.comentario;

import java.time.LocalDateTime;

public class DTOComentarioReq {
	// attributes
	private String contenido;
	private LocalDateTime fecha;
	private Long idUsuario;
	// constructors
	public DTOComentarioReq() {
	}
	public DTOComentarioReq(String contenido, LocalDateTime fecha, Long idUsuario) {
		this.contenido = contenido;
		this.fecha = fecha;
		this.idUsuario = idUsuario;
	}
	// getters and setters
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
