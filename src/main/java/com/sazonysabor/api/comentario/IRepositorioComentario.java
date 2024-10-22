package com.sazonysabor.api.comentario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioComentario extends JpaRepository<EntidadComentario, Long> {
	// define custom methods
}
