package com.sazonysabor.api.pago;

import java.util.List;

public interface IServicioPago {
	public List<EntidadPago> obtenerTodos();
	public EntidadPago obtenerUno(Long id);
	public EntidadPago guardar(EntidadPago pago);
	public void eliminar(Long id);
}
