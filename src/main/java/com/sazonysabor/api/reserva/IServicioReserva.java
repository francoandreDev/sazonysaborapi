package com.sazonysabor.api.reserva;

import java.util.List;

public interface IServicioReserva {
	public List<EntidadReserva> obtenerTodos();
	public EntidadReserva obtenerUno(Long id);
	public EntidadReserva guardar(EntidadReserva reserva);
	public void eliminar(Long id);
}
