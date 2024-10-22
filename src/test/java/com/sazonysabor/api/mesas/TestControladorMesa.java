package com.sazonysabor.api.mesas;

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
public class TestControladorMesa {
	@Autowired
	private MockMvc mockMvc;
	private String rutaBase = "/api/v1/mesas";
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
        String nuevaMesa = "{"
        		+ "\"capacidad\": 5,"
        		+ "\"estado\": true"
        		+ "}";
        mockMvc.perform(post(rutaBase)
                .contentType(MediaType.APPLICATION_JSON)
                .content(nuevaMesa))
                .andExpect(status().isCreated());
    }
    @Test
    public void testReemplazar() throws Exception {
        String mesaActualizada = "{"
        		+ "\"capacidad\": 10,"
        		+ "\"estado\": false"
        		+ "}";
        mockMvc.perform(put(rutaBase + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mesaActualizada))
                .andExpect(status().isOk());
    }
    @Test
    public void testActualizar() throws Exception {
        String camposActualizados = "{"
        		+ "\"estado\": true"
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
