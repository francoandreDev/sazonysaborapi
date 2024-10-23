package com.sazonysabor.api.reserva;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioReserva implements IServicioReserva {
	@Autowired
	private IRepositorioReserva repositorio;
	@Override
	public List<EntidadReserva> obtenerTodos() {
		return repositorio.findAll();
	}
	@Override
	public EntidadReserva obtenerUno(Long id) {
		return repositorio.findById(id).orElse(null);
	}
	@Override
	public EntidadReserva guardar(EntidadReserva reserva) {
		return repositorio.save(reserva);
	}
	@Override
	public void eliminar(Long id) {
		repositorio.deleteById(id);
	}
}
