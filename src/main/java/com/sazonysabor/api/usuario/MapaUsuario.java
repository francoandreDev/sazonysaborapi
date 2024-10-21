package com.sazonysabor.api.usuario;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MapaUsuario {
	// EntidadUsuario -> DTOUsuarioRes
	public DTOUsuarioRes obtenerRespuesta(EntidadUsuario usuario) {
		if(usuario == null) return null;
		
		return new DTOUsuarioRes(
			usuario.getId(), 
			usuario.getNombre(), 
			usuario.getCorreo(), 
			usuario.getContrasena(), 
			usuario.getTelefono(),
			usuario.getComentarios()
		);
	}
	
	// DTOUsuarioReq -> EntidadUsuario
	public EntidadUsuario obtenerEntidad(DTOUsuarioReq req) {
		if (req == null) return null;
		
		return new EntidadUsuario(
			null, 
			req.getNombre(), 
			req.getCorreo(), 
			req.getContrasena(), 
			req.getTelefono()
		);
	}
	
	// update EntidadUsuario with DTOUsuarioReq
	public EntidadUsuario actualizarEntidad(EntidadUsuario usuario, DTOUsuarioReq req) {
		usuario.setNombre(req.getNombre());
		usuario.setCorreo(req.getCorreo());
		usuario.setContrasena(req.getContrasena());
		usuario.setTelefono(req.getTelefono());
		return usuario;
	}
	
	// partial update EntidadUsuario
	public EntidadUsuario actualizarParcialEntidad(EntidadUsuario usuario, Map<String, Object> campos) {
		campos.forEach((clave, valor) -> {
			switch(clave) {
				case "nombre": 
					usuario.setNombre((String) valor);
					break;
				case "correo":
					usuario.setCorreo((String) valor);
					break;
				case "contrasena":
					usuario.setContrasena((String) valor);
					break;
				case "telefono":
					usuario.setTelefono((String) valor);
					break;
			}
		});
		return usuario;
	}
}
