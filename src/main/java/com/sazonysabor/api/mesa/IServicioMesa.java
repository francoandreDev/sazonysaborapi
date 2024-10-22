package com.sazonysabor.api.mesa;

import java.util.List;

public interface IServicioMesa {
	public List<EntidadMesa> obtenerTodos();
	public EntidadMesa obtenerUno(Long id);
	public EntidadMesa guardar(EntidadMesa mesa);
	public void eliminar(Long id);
}
