package com.sazonysabor.api.usuario;

import java.util.List;

public interface IServicioUsuario {
	public List<EntidadUsuario> obtenerTodos();
	public EntidadUsuario obtenerUno(Long id);
	public EntidadUsuario guardar(EntidadUsuario usuario);
	public void eliminar(Long id);
}
