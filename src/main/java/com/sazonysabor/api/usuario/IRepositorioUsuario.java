package com.sazonysabor.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioUsuario extends JpaRepository<EntidadUsuario, Long> {
	// define custom methods
}
