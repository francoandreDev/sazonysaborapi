package com.sazonysabor.api.comentario;

import java.util.List;

public interface IServicioComentario {
	public List<EntidadComentario> obtenerTodos();
	public EntidadComentario obtenerUno(Long id);
	public EntidadComentario guardar(EntidadComentario comentario);
	public void eliminar(Long id);
}
