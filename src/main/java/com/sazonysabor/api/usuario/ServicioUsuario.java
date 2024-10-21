package com.sazonysabor.api.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario implements IServicioUsuario{
	@Autowired
	private IRepositorioUsuario repositorio;

	@Override
	public List<EntidadUsuario> obtenerTodos() {
		return repositorio.findAll();
	}

	@Override
	public EntidadUsuario obtenerUno(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public EntidadUsuario guardar(EntidadUsuario usuario) {
		return repositorio.save(usuario);
	}

	@Override
	public void eliminar(Long id) {
		repositorio.deleteById(id);
	}

}
