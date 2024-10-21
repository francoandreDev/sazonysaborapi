package com.sazonysabor.api.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOUsuarioReq {
	// attributes
	private String correo;
	private String nombre;
	@JsonProperty("contraseña")
	private String contrasena;
	@JsonProperty("teléfono")
	private String telefono;
	// constructors
	public DTOUsuarioReq() {
	}
	public DTOUsuarioReq(String nombre, String correo, String contrasena, String telefono) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.telefono = telefono;
	}
	// getters y setters
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
