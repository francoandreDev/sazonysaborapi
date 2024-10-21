package com.sazonysabor.api.usuarios;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
public class TestControladorUsuario {
	 @Autowired
	    private MockMvc mockMvc;

    @Test
    public void testObtenerTodos() throws Exception {
        mockMvc.perform(get("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testObtenerUno() throws Exception {
        mockMvc.perform(get("/api/v1/usuarios/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testCrear() throws Exception {
        String nuevoUsuario = "{\"correo\":\"newuser@gmail.com\",\"nombre\":\"Nuevo Usuario\",\"contrasena\":\"Password123\",\"telefono\":\"+51 912 345 678\"}";
        
        mockMvc.perform(post("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nuevoUsuario))
                .andExpect(status().isCreated());
    }

    @Test
    public void testReemplazar() throws Exception {
        String usuarioActualizado = "{\"correo\":\"update@gmail.com\",\"nombre\":\"Usuario Actualizado\",\"contrasena\":\"Password123\",\"telefono\":\"+51 987 654 321\"}";

        mockMvc.perform(put("/api/v1/usuarios/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioActualizado))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testActualizar() throws Exception {
        String camposActualizados = "{\"nombre\":\"Nombre Actualizado\", \"telefono\":\"+51 912 345 678\"}";
        
        mockMvc.perform(patch("/api/v1/usuarios/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(camposActualizados))
                .andExpect(status().isOk());
    }

    @Test
    public void testEliminar() throws Exception {
    	
        mockMvc.perform(delete("/api/v1/usuarios/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
    @Test
    public void testVerOpciones() throws Exception {
        mockMvc.perform(options("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Allow", "GET, HEAD, POST, PUT, DELETE, OPTIONS, TRACE, PATCH"));
    }

}
