package com.sazonysabor.api.comentario;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sazonysabor.api.usuario.EntidadUsuario;
import com.sazonysabor.api.usuario.ServicioUsuario;

@Component
public class MapaComentario {
	@Autowired
	private ServicioUsuario servicioUsuario;
	// get user
	private EntidadUsuario obtenerUsuario(Long id) {
		return servicioUsuario.obtenerUno(id);
	}
	// EntidadComentario -> DTOComentarioRes
	public DTOComentarioRes obtenerRespuesta(EntidadComentario comentario) {
		if(comentario == null) return null;
		return new DTOComentarioRes(
			comentario.getId(),
			comentario.getContenido(),
			comentario.getFecha(),
			comentario.getUsuario().getId()
		);
	}
	// DTOComentarioReq -> EntidadComentario
	public EntidadComentario obtenerEntidad(DTOComentarioReq req) {
		if(req == null) return null;
		return new EntidadComentario (
			null,
			req.getContenido(),
			req.getFecha(),
			obtenerUsuario(req.getIdUsuario())
		);
	}
	// EntidadUsuario <= DTOComentarioReq
	public EntidadComentario actualizarEntidad(EntidadComentario comentario, DTOComentarioReq req) {
		comentario.setContenido(req.getContenido());
		comentario.setFecha(req.getFecha());
		comentario.setUsuario(obtenerUsuario(req.getIdUsuario()));
		return comentario;
	}
	// EntidadUsuario <- DTOComentarioReq
	public EntidadComentario actualizarParcialEntidad(EntidadComentario comentario, Map<String, Object> campos) {
		campos.forEach((clave, valor) -> {
			switch(clave) {
			case "contenido":
				comentario.setContenido((String) valor);
				break;
			case "fecha":
				comentario.setFecha(LocalDateTime.parse((String)valor));
				break;
			case "idUsuario":
				comentario.setUsuario(obtenerUsuario((Long) valor));
				break;
			}
		});
		return comentario;
	}
}
