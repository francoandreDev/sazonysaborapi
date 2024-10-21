package com.sazonysabor.api.usuario;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sazonysabor.api.comentario.EntidadComentario;

public class DTOUsuarioRes {
	// attributes
    private Long id;
	private String correo;
	private String nombre;
	@JsonProperty("contraseña")
	private String contrasena;
	@JsonProperty("teléfono")
	private String telefono;
	private List<EntidadComentario> comentarios = new ArrayList<>();
	// constructors
	public DTOUsuarioRes() {
	}
	public DTOUsuarioRes(Long id, String nombre, String correo, String contrasena, String telefono, List<EntidadComentario> comentarios) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.telefono = telefono;
		this.comentarios = comentarios;
	}
	// getters y setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<EntidadComentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<EntidadComentario> comentarios) {
		this.comentarios = comentarios;
	}
}
