package com.sazonysabor.api.comentario;

import java.time.LocalDateTime;

import com.sazonysabor.api.usuario.EntidadUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentarios")
public class EntidadComentario {
	// attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String contenido;
	private LocalDateTime fecha;
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private EntidadUsuario usuario;
	// constructores
	public EntidadComentario() {	
	}
	public EntidadComentario(Long id, String contenido, LocalDateTime fecha, EntidadUsuario usuario) {
		this.id = id;
		this.contenido = contenido;
		this.fecha = fecha;
		this.usuario = usuario;
	}
	// getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public EntidadUsuario getUsuario() {
		return usuario;
	}
	public void setUsuario(EntidadUsuario usuario) {
		this.usuario = usuario;
	}
}
