package com.sazonysabor.api.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOUsuarioRes {
	// attributes
    private Long id;
	private String correo;
	private String nombre;
	@JsonProperty("contraseña")
	private String contrasena;
	@JsonProperty("teléfono")
	private String telefono;
	// constructors
	public DTOUsuarioRes() {
	}
	public DTOUsuarioRes(Long id, String nombre, String correo, String contrasena, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.telefono = telefono;
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
}
