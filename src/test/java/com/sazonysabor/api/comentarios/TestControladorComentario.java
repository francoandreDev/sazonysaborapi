package com.sazonysabor.api.comentarios;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class TestControladorComentario {
	@Autowired
	private MockMvc mockMvc;
	private String rutaBase = "/api/v1/comentarios";
	@Test
	public void testObtenerTodos() throws Exception {
		mockMvc.perform(get(rutaBase)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	@Test
	public void testObtenerUno() throws Exception {
		mockMvc.perform(get(rutaBase + "/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	@Test
	public void testCrear() throws Exception {
		String nuevoComentario = "{"
				+ "\"contenido\": \"comentario1\","
				+ "\"fecha\": \" " + LocalDateTime.now().toString() + "\","
				+ "\"idUsuario\": \"1\""
				+ "}";
		mockMvc.perform(post(rutaBase)
				.contentType(MediaType.APPLICATION_JSON)
                .content(nuevoComentario))
                .andExpect(status().isCreated());
	}
	@Test
	public void testReemplazar() throws Exception {
		String comentarioActualizado = "{"
				+ "\"contenido\": \"comentando\","
				+ "\"fecha\":\" " + LocalDateTime.of(2024, 10, 21, 21, 03, 59).toString() + "\","
				+ "\"idUsuario\": \"1\""
				+ "}";
		mockMvc.perform(put(rutaBase + "/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
                .content(comentarioActualizado))
                .andExpect(status().isOk());
	}
	@Test
	public void testActualizar() throws Exception {
		String camposActualizados = "{"
				+ "\"contenido\": \"Qué bueno verte por aquí\""
				+ "}";
		mockMvc.perform(patch(rutaBase + "/{id}", 1)
			.contentType(MediaType.APPLICATION_JSON)
	        .content(camposActualizados))
	        .andExpect(status().isOk());
	}
	@Test
	public void testEliminar() throws Exception {
		mockMvc.perform(delete(rutaBase + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
	}
	@Test
	public void testVerOpciones() throws Exception {
		mockMvc.perform(options(rutaBase)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(
                	"Allow", "GET, HEAD, POST, PUT, DELETE, OPTIONS, TRACE, PATCH"
                ));
	}
}
