package com.sazonysabor.api.pago;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPago implements IServicioPago {
	@Autowired
	private IRepositorioPago repositorio;
	@Override
	public List<EntidadPago> obtenerTodos() {
		return repositorio.findAll();
	}
	@Override
	public EntidadPago obtenerUno(Long id) {
		return repositorio.findById(id).orElse(null);
	}
	@Override
	public EntidadPago guardar(EntidadPago pago) {
		return repositorio.save(pago);
	}
	@Override
	public void eliminar(Long id) {
		repositorio.deleteById(id);
	}
}
