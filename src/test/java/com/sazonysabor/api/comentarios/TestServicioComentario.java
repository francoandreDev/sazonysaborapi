package com.sazonysabor.api.comentarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sazonysabor.api.comentario.EntidadComentario;
import com.sazonysabor.api.comentario.IRepositorioComentario;
import com.sazonysabor.api.comentario.ServicioComentario;
import com.sazonysabor.api.usuario.EntidadUsuario;
import com.sazonysabor.api.usuario.IRepositorioUsuario;
import com.sazonysabor.api.usuario.ServicioUsuario;

public class TestServicioComentario {
	@InjectMocks
	private ServicioComentario servicio;
	@InjectMocks
	private ServicioUsuario servicioUsuario;
	@Mock
	private IRepositorioComentario repositorio;
	@Mock
	private IRepositorioUsuario repositorioUsuario;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	private EntidadUsuario obtenerUsuario(Long id) {
		return servicioUsuario.obtenerUno(id);
	}
	@Test
	void testObtenerTodos() {
		EntidadComentario comentario1 = new EntidadComentario(
			1L, "comentario1", LocalDateTime.now(), obtenerUsuario(1L)
		);
		EntidadComentario comentario2 = new EntidadComentario(
			2L, "Qué rico comer aquí", LocalDateTime.now(), obtenerUsuario(1L)
		);
		when(repositorio.findAll()).thenReturn(Arrays.asList(comentario1, comentario2));
		List<EntidadComentario> comentarios = servicio.obtenerTodos();
		assertEquals(2, comentarios.size());
		verify(repositorio, times(1)).findAll();
	}
	@Test
	void testObtenerUno() {
		EntidadComentario comentario = new EntidadComentario(
			1L, "comentario1", LocalDateTime.of(2022, 04, 21, 20, 40, 59), obtenerUsuario(1L)
		);
		when(repositorio.findById(1L)).thenReturn(Optional.of(comentario));
		EntidadComentario comentarioObtenido = servicio.obtenerUno(1L);
		assertNotNull(comentarioObtenido);
		assertEquals("comentario1", comentarioObtenido.getContenido());
		assertEquals(LocalDateTime.of(2022, 04, 21, 20, 40, 59), comentarioObtenido.getFecha());
		assertEquals(obtenerUsuario(1L), comentarioObtenido.getUsuario());
	}
	@Test
	void testGuardar() {
		EntidadUsuario usuarioSimulado = new EntidadUsuario(
			1L, "Juan Perez", "juanperez@gmail.com", "contraseña123", "+51 900 000 000"
		);
	    when(repositorioUsuario.findById(1L)).thenReturn(Optional.of(usuarioSimulado)); 
	    EntidadComentario comentario = new EntidadComentario(
	    	null, "Nuevo Comentario", LocalDateTime.of(2024, 05, 25, 10, 07, 01), usuarioSimulado
	    );
	    when(repositorio.save(comentario)).thenReturn(
	    	new EntidadComentario(1L, "Nuevo Comentario", LocalDateTime.now(), usuarioSimulado)
	    );
	    EntidadComentario comentarioGuardado = servicio.guardar(comentario);
	    assertNotNull(comentarioGuardado.getId());
	    assertEquals(1L, comentarioGuardado.getId());
	}
	@Test
	void testEliminar() {
		Long id = 1L;
		servicio.eliminar(id);
		verify(repositorio, times(1)).deleteById(id);
	}
}
