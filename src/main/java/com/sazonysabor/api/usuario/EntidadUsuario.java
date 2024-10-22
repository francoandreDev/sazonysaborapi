package com.sazonysabor.api.usuario;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sazonysabor.api.comentario.EntidadComentario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class EntidadUsuario {
	// attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String correo;
	private String nombre;
	@JsonProperty("contraseña")
	private String contrasena;
	@JsonProperty("teléfono")
	private String telefono;
	// inversion relation with comments table
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EntidadComentario> comentarios = new ArrayList<>();
	// constructors
	public EntidadUsuario() {
	}
	public EntidadUsuario(Long id, String nombre, String correo, String contrasena, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.telefono = telefono;
	}
	// getters and setters
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
	public void comentar(EntidadComentario comentario) {
		comentarios.add(comentario);
		comentario.setUsuario(this);
	}
	public void borrarComentario(EntidadComentario comentario) {
		comentarios.remove(comentario);
		comentario.setUsuario(null);
	}
}
