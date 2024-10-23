package com.sazonysabor.api.reservas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class TestControladorReserva {
	@Autowired
	private MockMvc mockMvc;
	private String rutaBase = "/api/v1/reservas";
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
        String nuevoUsuario = "{"
        		+ "\"fecha\": \"2020-10-10T19:19:19\","
        		+ "\"personas\": 17,"
        		+ "\"estado\": \"Reservado\""
        		+ "}";
        mockMvc.perform(post(rutaBase)
                .contentType(MediaType.APPLICATION_JSON)
                .content(nuevoUsuario))
                .andExpect(status().isCreated());
    }
    @Test
    public void testReemplazar() throws Exception {
        String usuarioActualizado = "{"
        		+ "\"fecha\": \"2020-10-10T19:19:25\","
        		+ "\"personas\": 7,"
        		+ "\"estado\": \"Cancelado\""
        		+ "}";
        mockMvc.perform(put(rutaBase + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioActualizado))
                .andExpect(status().isOk());
    }
    @Test
    public void testActualizar() throws Exception {
        String camposActualizados = "{"
        		+ "\"fecha\": \"2020-10-10T19:49:00\","
        		+ "\"estado\": \"Cancelado\""
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
