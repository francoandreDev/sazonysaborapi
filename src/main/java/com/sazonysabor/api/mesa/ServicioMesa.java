package com.sazonysabor.api.mesa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioMesa implements IServicioMesa {
	@Autowired
	private IRepositorioMesa repositorio;
	@Override
	public List<EntidadMesa> obtenerTodos() {
		return repositorio.findAll();
	}
	@Override
	public EntidadMesa obtenerUno(Long id) {
		return repositorio.findById(id).orElse(null);
	}
	@Override
	public EntidadMesa guardar(EntidadMesa mesa) {
		return repositorio.save(mesa);
	}
	@Override
	public void eliminar(Long id) {
		repositorio.deleteById(id);
	}
}
