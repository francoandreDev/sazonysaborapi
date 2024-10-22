package com.sazonysabor.api.comentario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioComentario implements IServicioComentario {
	@Autowired
	private IRepositorioComentario repositorio;
	@Override
	public List<EntidadComentario> obtenerTodos() {
		return repositorio.findAll();
	}
	@Override
	public EntidadComentario obtenerUno(Long id) {
		return repositorio.findById(id).orElse(null);
	}
	@Override
	public EntidadComentario guardar(EntidadComentario comentario) {
		return repositorio.save(comentario);
	}
	@Override
	public void eliminar(Long id) {
		repositorio.deleteById(id);
	}
}
